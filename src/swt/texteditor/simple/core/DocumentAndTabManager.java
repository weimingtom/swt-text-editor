package swt.texteditor.simple.core;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import swt.texteditor.simple.core.datamodel.Document;
import swt.texteditor.simple.core.listeners.DocumentTabDisposeListener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DocumentAndTabManager {
	private static final Logger LOGGER = Logger.getLogger(DocumentAndTabManager.class);
	private final CTabFolder cTabFolder;
	private CTabItem currentTabItem;
	private Object currentTabItemLock;
	private final Map<Document, CTabItem> documentsToTabs;

	public Document getDocument(final CTabItem cTabItem) {
		synchronized (documentsToTabs) {
			for (final Map.Entry<Document, CTabItem> docToTabEntry : documentsToTabs.entrySet()) {
				if (docToTabEntry.getValue().equals(cTabItem)) {
					return docToTabEntry.getKey();
				}
			}
		}
		return null;
	}

	public DocumentAndTabManager(final Shell shell) {
		cTabFolder = new CTabFolder(shell, SWT.NONE);
		documentsToTabs = new HashMap<Document, CTabItem>();
		currentTabItemLock = new Object();
		cTabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent selectionEvent) {
				final CTabItem tab = ((CTabFolder) selectionEvent.getSource()).getSelection();
				synchronized (currentTabItemLock) {
					currentTabItem = tab;
				}
			}
		});
	}

	public CTabItem getCurrentTabItem() {
		synchronized (this) {
			return currentTabItem;
		}
	}

	public CTabItem addOrOpenExisting(Document document) {
		final CTabItem tab;
		synchronized (documentsToTabs) {
			final CTabItem cTabItem = documentsToTabs.get(document);
			if (cTabItem == null) {
				tab = new CTabItem(cTabFolder, SWT.CLOSE);
				tab.addDisposeListener(new DocumentTabDisposeListener(this));
				final Text text = new Text(cTabFolder, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
				text.setText(document.getContent());
				tab.setControl(text);
				if (!document.isTemporary()) {
					tab.setText(document.getPath().getName());
				} else {
					tab.setText("<Temp>" + document.hashCode());
				}
				documentsToTabs.put(document, tab);
			} else {
				tab = cTabItem;
			}
			synchronized (currentTabItemLock) {
				currentTabItem = tab;
				cTabFolder.setSelection(currentTabItem);
			}
		}
		if (LOGGER.isInfoEnabled())
			LOGGER.info(tab + " <-> " + document.getPath());
		return tab;
	}

	public CTabItem saveAs(final CTabItem tab, Document document, String newPath) {
		final Document newDocument = new Document(new File(newPath), document.getContent(), false);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Saving document as " + newDocument.getPath());
		try {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Saving file " + newDocument.getPath());
			FileUtils.write(newDocument.getPath(), newDocument.getContent());
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Saved file " + newDocument.getPath());
			synchronized (documentsToTabs) {
				removeDocumentByTab(tab);// remove old tabs
				documentsToTabs.put(newDocument, tab);
				tab.setText(newDocument.getPath().getName());
				synchronized (currentTabItemLock) {
					currentTabItem = tab;
				}
				cTabFolder.setSelection(tab);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return tab;
	}

	public CTabItem save(CTabItem tab, Document document) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Saving document " + document.getPath());
		return saveAs(tab, document, document.getPath().getAbsolutePath());
	}

	public void reload(final Document documentInTab, final Document freshDocument) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Reloading " + documentInTab.getPath());
		synchronized (documentsToTabs) {
			final CTabItem cTabItem = documentsToTabs.get(documentInTab);
			final Text text = ((Text) cTabItem.getControl());
			text.setText(freshDocument.getContent());
			documentsToTabs.put(freshDocument, cTabItem);
		}
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Hopefully reloaded " + documentInTab.getPath());
	}

	public void removeDocumentByTab(CTabItem tab) {
		synchronized (documentsToTabs) {
			Iterator<Map.Entry<Document, CTabItem>> iterator = documentsToTabs.entrySet().iterator();
			while (iterator.hasNext()) {
				final Map.Entry<Document, CTabItem> next = iterator.next();
				if (next.getValue().equals(tab)) {
					iterator.remove();
				}
			}
		}
	}
}
