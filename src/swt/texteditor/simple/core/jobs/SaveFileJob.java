package swt.texteditor.simple.core.jobs;

import org.eclipse.swt.custom.CTabItem;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

public class SaveFileJob implements Runnable {
    private final DocumentAndTabManager documentAndTabManager;
    private final Document document;
    private final CTabItem cTabItem;

    public SaveFileJob(DocumentAndTabManager documentAndTabManager, CTabItem cTabItem, Document document) {
        this.documentAndTabManager = documentAndTabManager;
        this.cTabItem = cTabItem;
        this.document = document;
    }

    @Override
    public void run() {
        documentAndTabManager.save(cTabItem, document);
    }
}
