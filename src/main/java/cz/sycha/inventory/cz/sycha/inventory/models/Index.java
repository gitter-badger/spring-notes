package cz.sycha.inventory.cz.sycha.inventory.models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Index {
    private final long id;
    private final Map<String, String> info = new HashMap<String, String>();

    public Index(long id) {
        this.id = id;

        info.put("Version", "1.0.0");
        info.put("Whatever", "Else");
    }

    public long getId() {
        return id;
    }

    public Map<String, String> getInfo() {
        return info;
    }
}

