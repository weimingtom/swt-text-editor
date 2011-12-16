package swt.texteditor.simple.core.listeners;

import org.apache.log4j.Logger;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.jobs.ReloadJob;

public class FileMenuReloadSelectionListener extends SelectionAdapter {
    private static final Logger LOGGER = Logger.getLogger(FileMenuReloadSelectionListener.class);
    private final DocumentAndTabManager documentAndTabManager;
    private final Shell shell;

    public FileMenuReloadSelectionListener(Shell shell, DocumentAndTabManager documentAndTabManager) {
        this.documentAndTabManager = documentAndTabManager;
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        final CTabItem currentTab = documentAndTabManager.getCurrentTabItem();
        if (currentTab != null) {
            if (LOGGER.isDebugEnabled()) LOGGER.debug("Submitting reload job...");
            shell.getDisplay().asyncExec(new ReloadJob(documentAndTabManager, currentTab));
            if (LOGGER.isDebugEnabled()) LOGGER.debug("Submitted reload job...");
        } else {
            if (LOGGER.isDebugEnabled()) LOGGER.debug("Tab not found.");
        }
    }
}
