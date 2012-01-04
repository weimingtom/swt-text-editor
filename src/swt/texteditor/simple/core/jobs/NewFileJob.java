package swt.texteditor.simple.core.jobs;

import static java.io.File.createTempFile;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.widgets.Shell;

import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

public class NewFileJob implements Runnable {
	private final DocumentAndTabManager documentAndTabManager;
	private final Shell shell;

	public NewFileJob(final DocumentAndTabManager documentAndTabManager, final Shell shell) {
		this.documentAndTabManager = documentAndTabManager;
		this.shell = shell;
	}

	@Override
	public void run() {
		try {
			final File tempFile = createTempFile("swt_simple_editor", "tmp");
			tempFile.deleteOnExit();
			shell.getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					documentAndTabManager.addOrOpenExisting(new Document(tempFile, "", true));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
