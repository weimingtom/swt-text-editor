package swt.texteditor.simple.core.listeners;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class HelpMenuAboutSelectionListener extends SelectionAdapter {

	private final Shell shell;

	public HelpMenuAboutSelectionListener(final Shell shell) {
		this.shell = shell;
	}

	@Override
	public void widgetSelected(final SelectionEvent selectionEvent) {
		final MessageBox aboutDialog = new MessageBox(shell);
		aboutDialog.setText("About dialog.");
		aboutDialog.setMessage("Created by @Me");
		aboutDialog.open();
	}
}
