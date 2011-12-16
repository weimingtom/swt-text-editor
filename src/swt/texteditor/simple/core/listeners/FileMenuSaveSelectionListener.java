package swt.texteditor.simple.core.listeners;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;
import swt.texteditor.simple.core.jobs.SaveFileAsJob;
import swt.texteditor.simple.core.jobs.SaveFileJob;

public class FileMenuSaveSelectionListener extends SelectionAdapter {
    private static final Logger LOGGER = Logger.getLogger(FileMenuSaveSelectionListener.class);
    private final DocumentAndTabManager documentAndTabManager;
    private final Shell shell;

    public FileMenuSaveSelectionListener(Shell shell, DocumentAndTabManager documentAndTabManager) {
        this.documentAndTabManager = documentAndTabManager;
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        CTabItem currentTab = documentAndTabManager.getCurrentTabItem();
        if (currentTab != null) {
            final Document document = documentAndTabManager.getDocument(currentTab);
            if (document != null) {
                final Document documentToSave = new Document(document.getPath(), ((Text) currentTab.getControl()).getText(), document.isTemporary());
                if (document.isTemporary()) {
                    if (LOGGER.isDebugEnabled()) LOGGER.debug("Submitting save as job...");
                    selectWhereToSaveAndSumbitFileSaveAsJob(documentToSave);
                    if (LOGGER.isDebugEnabled()) LOGGER.debug("Submitted save as job.");
                } else {
                    if (LOGGER.isDebugEnabled()) LOGGER.debug("Submitting save job...");
                    shell.getDisplay().asyncExec(new SaveFileJob(documentAndTabManager, currentTab, documentToSave));
                    if (LOGGER.isDebugEnabled()) LOGGER.debug("Submitted save job.");
                }
            }
        } else {
            if (LOGGER.isDebugEnabled()) LOGGER.debug("current tab is null");
        }
    }

    private void selectWhereToSaveAndSumbitFileSaveAsJob(Document document) {
        final FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        fileDialog.setText("Please select which file to save.");
        final String newPath = fileDialog.open();
        final CTabItem currentTab = documentAndTabManager.getCurrentTabItem();
        if (newPath != null) {
            final Document documentToSave = new Document(document.getPath(), ((Text) currentTab.getControl()).getText(), document.isTemporary());
            if (LOGGER.isDebugEnabled()) LOGGER.debug("Submitting save as job...");
            shell.getDisplay().asyncExec(new SaveFileAsJob(documentAndTabManager, currentTab, documentToSave, newPath));
            if (LOGGER.isDebugEnabled()) LOGGER.debug("Submitted save as job.");
        } else {
            if (LOGGER.isDebugEnabled()) LOGGER.debug("No file selected");
        }
    }
}
