package swt.texteditor.simple.core.listeners;

import org.apache.log4j.Logger;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

public class FileMenuExitSelectionListener extends SelectionAdapter {
    private static final Logger LOGGER = Logger.getLogger(FileMenuExitSelectionListener.class);
    private final Shell shell;

    public FileMenuExitSelectionListener(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        if (LOGGER.isInfoEnabled()) LOGGER.info("Exiting...");
        shell.dispose();
    }
}
