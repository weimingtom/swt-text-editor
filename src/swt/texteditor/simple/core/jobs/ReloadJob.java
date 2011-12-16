package swt.texteditor.simple.core.jobs;

import org.eclipse.swt.custom.CTabItem;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

public class ReloadJob implements Runnable {
    private final DocumentAndTabManager documentAndTabManager;
    private final CTabItem tabToReload;

    public ReloadJob(DocumentAndTabManager documentAndTabManager, CTabItem tabToReload) {
        this.documentAndTabManager = documentAndTabManager;
        this.tabToReload = tabToReload;
    }

    @Override
    public void run() {
        final Document document = documentAndTabManager.getDocument(tabToReload);
        if (document != null) {
            documentAndTabManager.reload(document);
        }
    }
}