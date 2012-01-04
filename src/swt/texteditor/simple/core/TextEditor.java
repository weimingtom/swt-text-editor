package swt.texteditor.simple.core;

import java.util.concurrent.ExecutorService;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import swt.texteditor.simple.core.listeners.*;

public class TextEditor {
	private final Shell shell;
	private final DocumentAndTabManager documentAndTabManager;
	private final ExecutorService executorService;

	public TextEditor(final Shell shell, final DocumentAndTabManager documentAndTabManager,
			final ExecutorService executorService) {
		this.shell = shell;
		this.documentAndTabManager = documentAndTabManager;
		this.executorService = executorService;
	}

	public void run() {
		shell.open();

		while (!shell.isDisposed()) {
			final Display display = shell.getDisplay();
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		shell.dispose();
	}

	public void init() {
		shell.setLayout(new FillLayout());

		shell.setMenuBar(createAndSetUpMenu());
	}

	private Menu createAndSetUpMenu() {
		final Menu menu = new Menu(shell, SWT.BAR);

		// Create all the items in the bar menu
		final MenuItem fileItem = new MenuItem(menu, SWT.CASCADE);
		fileItem.setText("&File");
		final MenuItem helpItem = new MenuItem(menu, SWT.CASCADE);
		helpItem.setText("&Help");

		createAndSetUpFileMenu(menu, fileItem);
		createAndSetUpHelpMenu(menu, helpItem);
		return menu;
	}

	private void createAndSetUpFileMenu(Menu menu, MenuItem fileItem) {
		final Menu fileMenu = new Menu(menu);
		fileItem.setMenu(fileMenu);
		setUpFileMenu(fileMenu);
	}

	private void createAndSetUpHelpMenu(Menu menu, MenuItem helpItem) {
		final Menu helpMenu = new Menu(menu);
		helpItem.setMenu(helpMenu);
		final MenuItem about = new MenuItem(helpMenu, SWT.NONE);
		about.setText("About");
		about.addSelectionListener(new HelpMenuAboutSelectionListener(shell));
	}

	private void setUpFileMenu(Menu fileMenu) {
		setUpFileMenuNewAction(fileMenu);
		setUpFileMenuOpenAction(fileMenu);

		new MenuItem(fileMenu, SWT.SEPARATOR);

		setUpFileMenuReloadAction(fileMenu);

		new MenuItem(fileMenu, SWT.SEPARATOR);

		setUpFileMenuSaveAction(fileMenu);
		setUpFileMenuSaveAsAction(fileMenu);

		new MenuItem(fileMenu, SWT.SEPARATOR);

		setUpFileMenuExitAction(fileMenu);
	}

	private void setUpFileMenuReloadAction(Menu fileMenu) {
		MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
		exitItem.setText("&Reload");
		exitItem.addSelectionListener(new FileMenuReloadSelectionListener(shell, documentAndTabManager, executorService));
	}

	private void setUpFileMenuExitAction(Menu fileMenu) {
		MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
		exitItem.setText("&Exit");
		exitItem.addSelectionListener(new FileMenuExitSelectionListener(shell));
	}

	private void setUpFileMenuSaveAsAction(Menu fileMenu) {
		MenuItem saveAsItem = new MenuItem(fileMenu, SWT.NONE);
		saveAsItem.addSelectionListener(new FileMenuSaveAsSelectionListener(shell, documentAndTabManager, executorService));
		saveAsItem.setText("Sa&ve As...");
	}

	private void setUpFileMenuSaveAction(Menu fileMenu) {
		MenuItem saveItem = new MenuItem(fileMenu, SWT.NONE);
		saveItem.setText("&Save");
		saveItem.addSelectionListener(new FileMenuSaveSelectionListener(shell, documentAndTabManager, executorService));
	}

	private void setUpFileMenuOpenAction(Menu fileMenu) {
		MenuItem openItem = new MenuItem(fileMenu, SWT.NONE);
		openItem.setText("&Open...");
		openItem.addSelectionListener(new FileMenuOpenSelectionListener(shell, documentAndTabManager, executorService));
	}

	private void setUpFileMenuNewAction(Menu fileMenu) {
		MenuItem newItem = new MenuItem(fileMenu, SWT.NONE);
		newItem.setText("&New");
		newItem.addSelectionListener(new FileMenuNewSelectionListener(shell, documentAndTabManager, executorService));
	}
}