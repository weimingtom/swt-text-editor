package swt.texteditor.simple.core.jobs;

import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Shell;

import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

public class SaveFileJob implements Runnable {
	private final DocumentAndTabManager documentAndTabManager;
	private final Document document;
	private final CTabItem cTabItem;
	private final Shell shell;

	public SaveFileJob(final DocumentAndTabManager documentAndTabManager, final CTabItem cTabItem, final Document document, final Shell shell) {
		this.documentAndTabManager = documentAndTabManager;
		this.cTabItem = cTabItem;
		this.document = document;
		this.shell = shell;
	}

	@Override
	public void run() {
		shell.getDisplay().asyncExec(new Runnable() {			
			@Override
			public void run() {
				documentAndTabManager.save(cTabItem, document);
			}
		});
	}
}
