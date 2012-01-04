package swt.texteditor.simple.core.listeners;

import java.util.concurrent.ExecutorService;

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
	private final ExecutorService executorService;

	public FileMenuReloadSelectionListener(final Shell shell, final DocumentAndTabManager documentAndTabManager,
			final ExecutorService executorService) {
		this.documentAndTabManager = documentAndTabManager;
		this.shell = shell;
		this.executorService = executorService;
	}

	@Override
	public void widgetSelected(final SelectionEvent selectionEvent) {
		final CTabItem currentTab = documentAndTabManager.getCurrentTabItem();
		if (currentTab != null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Submitting reload job...");
			executorService.execute(new ReloadJob(documentAndTabManager, currentTab, shell));
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Submitted reload job...");
		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Tab not found.");
		}
	}
}
