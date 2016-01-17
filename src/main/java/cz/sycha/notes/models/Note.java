package cz.sycha.notes.models;

import org.springframework.data.annotation.Id;

public class Note {
    @Id
    private final long id;
    private final String title;
    private final String content;

    public Note(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return id + "::" + title + "::" + content;
    }
}
