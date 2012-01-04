package swt.texteditor.simple.core.listeners;

import java.util.concurrent.ExecutorService;

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
	private final ExecutorService executorService;

	public FileMenuNewSelectionListener(final Shell shell, final DocumentAndTabManager documentAndTabManager,
			final ExecutorService executorService) {
		this.shell = shell;
		this.documentAndTabManager = documentAndTabManager;
		this.executorService = executorService;
	}

	@Override
	public void widgetSelected(final SelectionEvent selectionEvent) {
		if (LOGGER.isInfoEnabled())
			LOGGER.info("New command called...");
		if (LOGGER.isInfoEnabled())
			LOGGER.info("Submitting New File Job");
		executorService.execute(new NewFileJob(documentAndTabManager, shell));
		if (LOGGER.isInfoEnabled())
			LOGGER.info("New File Job Submitted");
	}
}
