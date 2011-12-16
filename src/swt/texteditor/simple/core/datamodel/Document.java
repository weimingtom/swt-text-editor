package swt.texteditor.simple.core.datamodel;

import java.io.File;

public class Document {
    private final File path;
    private final String content;
    private final boolean temporary;

    public Document(File path, String content, boolean temporary) {
        this.path = path;
        this.content = content;
        this.temporary = temporary;
    }

    public File getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }

    public boolean isTemporary() {
        return temporary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (temporary != document.temporary) return false;
        if (content != null ? !content.equals(document.content) : document.content != null) return false;
        if (path != null ? !path.equals(document.path) : document.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (temporary ? 1 : 0);
        return result;
    }
}
