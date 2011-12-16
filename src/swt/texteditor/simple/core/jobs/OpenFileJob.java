package swt.texteditor.simple.core.jobs;

import org.apache.commons.io.FileUtils;
import swt.texteditor.simple.core.DocumentAndTabManager;
import swt.texteditor.simple.core.datamodel.Document;

import java.io.File;
import java.io.IOException;

public class OpenFileJob implements Runnable {
    private final String pathToFile;
    private final DocumentAndTabManager documentAndTabManager;

    public OpenFileJob(final String pathToFile, final DocumentAndTabManager documentAndTabManager) {
        this.pathToFile = pathToFile;
        this.documentAndTabManager = documentAndTabManager;
    }

    @Override
    public void run() {
        final File fileToOpen = new File(pathToFile);
        try {
            final String content = FileUtils.readFileToString(fileToOpen);
            final Document document = new Document(fileToOpen, content, false);
            documentAndTabManager.addOrOpenExisting(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
