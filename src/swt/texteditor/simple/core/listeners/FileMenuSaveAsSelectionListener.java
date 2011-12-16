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

public class FileMenuSaveAsSelectionListener extends SelectionAdapter {
    private final Logger LOGGER = Logger.getLogger(FileMenuSaveAsSelectionListener.class);
    private final DocumentAndTabManager documentAndTabManager;
    private final Shell shell;

    public FileMenuSaveAsSelectionListener(Shell shell, DocumentAndTabManager documentAndTabManager) {
        this.documentAndTabManager = documentAndTabManager;
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        final CTabItem currentTab = documentAndTabManager.getCurrentTabItem();
        if (currentTab != null) {
            final Document document = documentAndTabManager.getDocument(currentTab);
            if (document != null) {
                selectWhereToSaveAndSumbitFileSaveAsJob(document);
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
