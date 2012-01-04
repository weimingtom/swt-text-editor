package swt.texteditor.simple.core.listeners;

import org.apache.log4j.Logger;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

public class FileMenuExitSelectionListener extends SelectionAdapter {
	private static final Logger LOGGER = Logger.getLogger(FileMenuExitSelectionListener.class);
	private final Shell shell;

	public FileMenuExitSelectionListener(final Shell shell) {
		this.shell = shell;
	}

	@Override
	public void widgetSelected(final SelectionEvent selectionEvent) {
		if (LOGGER.isInfoEnabled())
			LOGGER.info("Exiting...");
		shell.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				shell.dispose();
			}
		});
	}
}
