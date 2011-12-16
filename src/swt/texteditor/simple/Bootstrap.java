package swt.texteditor.simple;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.TextEditor;


public class Bootstrap {
    public static void main(final String[] args) {
        final Display display = new Display();
        final Shell shell = new Shell(display);
        final DocumentAndTabManager documentAndTabManager = new DocumentAndTabManager(shell);
        final TextEditor textEditor = new TextEditor(shell, documentAndTabManager);
        textEditor.init();
        textEditor.run();
    }
}
