package swt.texteditor.simple.core.jobs;

import org.eclipse.swt.custom.CTabItem;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

public class SaveFileAsJob implements Runnable {
    private final DocumentAndTabManager documentAndTabManager;
    private final Document document;
    private final String newPath;
    private final CTabItem cTabItem;

    public SaveFileAsJob(DocumentAndTabManager documentAndTabManager, CTabItem cTabItem, Document document, String newPath) {
        this.documentAndTabManager = documentAndTabManager;
        this.cTabItem = cTabItem;
        this.document = document;
        this.newPath = newPath;
    }

    @Override
    public void run() {
        documentAndTabManager.saveAs(cTabItem, document, newPath);
    }
}
