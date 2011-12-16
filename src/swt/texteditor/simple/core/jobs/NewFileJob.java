package swt.texteditor.simple.core.jobs;

import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

import java.io.File;
import java.io.IOException;

public class NewFileJob implements Runnable {
    private final DocumentAndTabManager documentAndTabManager;

    public NewFileJob(DocumentAndTabManager documentAndTabManager) {
        this.documentAndTabManager = documentAndTabManager;
    }

    @Override
    public void run() {
        try {
            final File tempFile = File.createTempFile("swt_simple_editor", "tmp");
            tempFile.deleteOnExit();
            documentAndTabManager.addOrOpenExisting(new Document(tempFile, "", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
