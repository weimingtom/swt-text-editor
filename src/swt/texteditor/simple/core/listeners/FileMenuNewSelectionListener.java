package swt.texteditor.simple.core.listeners;

import org.apache.log4j.Logger;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.jobs.NewFileJob;

public class FileMenuNewSelectionListener extends SelectionAdapter {
    private static Logger LOGGER = Logger.getLogger(FileMenuNewSelectionListener.class);
    private final Shell shell;
    private final DocumentAndTabManager documentAndTabManager;

    public FileMenuNewSelectionListener(Shell shell, DocumentAndTabManager documentAndTabManager) {
        this.shell = shell;
        this.documentAndTabManager = documentAndTabManager;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        if (LOGGER.isInfoEnabled()) LOGGER.info("New command called...");
        if (LOGGER.isInfoEnabled()) LOGGER.info("Submitting New File Job");
        shell.getDisplay().asyncExec(new NewFileJob(documentAndTabManager));
        if (LOGGER.isInfoEnabled()) LOGGER.info("New File Job Submitted");
    }
}
