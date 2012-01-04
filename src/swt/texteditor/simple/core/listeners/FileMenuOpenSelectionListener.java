package swt.texteditor.simple.core.listeners;

import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.jobs.OpenFileJob;

public class FileMenuOpenSelectionListener extends SelectionAdapter {
	private static final Logger LOGGER = Logger.getLogger(FileMenuOpenSelectionListener.class);
	private final Shell shell;
	private final DocumentAndTabManager documentAndTabManager;
	private final ExecutorService executorService;

	public FileMenuOpenSelectionListener(Shell shell, DocumentAndTabManager documentAndTabManager,
			ExecutorService executorService) {
		this.shell = shell;
		this.documentAndTabManager = documentAndTabManager;
		this.executorService = executorService;
	}

	@Override
	public void widgetSelected(SelectionEvent selectionEvent) {
		if (LOGGER.isInfoEnabled())
			LOGGER.info("Open command called...");
		final FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		final String fileToOpen = fileDialog.open();
		if (fileToOpen != null) {
			if (LOGGER.isInfoEnabled())
				LOGGER.info("Submitting Open File Job" + fileToOpen);
			executorService.execute(new OpenFileJob(fileToOpen, documentAndTabManager, shell));
			if (LOGGER.isInfoEnabled())
				LOGGER.info("Open File Job Submitted" + fileToOpen);
		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("No file selected.");
		}
	}
}
