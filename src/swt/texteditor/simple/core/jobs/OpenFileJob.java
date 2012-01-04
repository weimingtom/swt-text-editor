package swt.texteditor.simple.core.jobs;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.widgets.Shell;

import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

import java.io.File;
import java.io.IOException;

public class OpenFileJob implements Runnable {
	private final String pathToFile;
	private final DocumentAndTabManager documentAndTabManager;
	private final Shell shell;

	public OpenFileJob(final String pathToFile, final DocumentAndTabManager documentAndTabManager, final Shell shell) {
		this.pathToFile = pathToFile;
		this.documentAndTabManager = documentAndTabManager;
		this.shell = shell;
	}

	@Override
	public void run() {
		final File fileToOpen = new File(pathToFile);
		try {
			final String content = FileUtils.readFileToString(fileToOpen);
			final Document document = new Document(fileToOpen, content, false);
			shell.getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					documentAndTabManager.addOrOpenExisting(document);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
