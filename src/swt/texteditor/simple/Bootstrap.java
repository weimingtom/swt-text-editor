package swt.texteditor.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.TextEditor;

public class Bootstrap {
	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		final DocumentAndTabManager documentAndTabManager = new DocumentAndTabManager(shell);
		final ExecutorService executorService = Executors.newFixedThreadPool(30);
		final TextEditor textEditor = new TextEditor(shell, documentAndTabManager, executorService);
		textEditor.init();
		textEditor.run();
	}
}
