package swt.texteditor.simple.core.jobs;

import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Shell;

import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

public class SaveFileAsJob implements Runnable {
	private final DocumentAndTabManager documentAndTabManager;
	private final Document document;
	private final String newPath;
	private final CTabItem cTabItem;
	private final Shell shell;

	public SaveFileAsJob(final DocumentAndTabManager documentAndTabManager, final CTabItem cTabItem, final Document document,
			final String newPath, final Shell shell) {
		this.documentAndTabManager = documentAndTabManager;
		this.cTabItem = cTabItem;
		this.document = document;
		this.newPath = newPath;
		this.shell = shell;
	}

	@Override
	public void run() {
		shell.getDisplay().asyncExec(new Runnable() {			
			@Override
			public void run() {
				documentAndTabManager.saveAs(cTabItem, document, newPath);
			}
		});
	}
}
