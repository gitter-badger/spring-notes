package cz.sycha.notes.models;

import java.util.HashMap;
import java.util.Map;

public class Index {
    private final long id;
    private final Map<String, String> info = new HashMap<String, String>();

    public Index(long id) {
        this.id = id;

        info.put("version", "1.0-RC");
        info.put("information", "This is the API frontend of the Java/Spring note-taking app.");
    }

    public long getId() {
        return id;
    }

    public Map<String, String> getInfo() {
        return info;
    }
}

