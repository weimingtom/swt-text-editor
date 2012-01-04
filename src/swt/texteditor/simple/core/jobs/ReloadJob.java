package swt.texteditor.simple.core.jobs;

import static org.apache.commons.io.FileUtils.readFileToString;

import java.io.IOException;

import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Shell;

import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

public class ReloadJob implements Runnable {
	private final DocumentAndTabManager documentAndTabManager;
	private final CTabItem tabToReload;
	private final Shell shell;

	public ReloadJob(final DocumentAndTabManager documentAndTabManager, final CTabItem tabToReload, final Shell shell) {
		this.documentAndTabManager = documentAndTabManager;
		this.tabToReload = tabToReload;
		this.shell = shell;
	}

	@Override
	public void run() {
		final Document document = documentAndTabManager.getDocument(tabToReload);
		if (document != null) {
			// TODO: move here logic for document reload.
			try {
				final String content = readFileToString(document.getPath());
				final Document newDocument = new Document(document.getPath(), content, document.isTemporary());
				shell.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						documentAndTabManager.reload(document, newDocument);
					}
				});
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
}