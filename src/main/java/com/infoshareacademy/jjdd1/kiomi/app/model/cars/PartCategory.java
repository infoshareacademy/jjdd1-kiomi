package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import com.infoshareacademy.jjdd1.kiomi.TerminalMenu;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * "id": "7sy",
 * "name": "Nadwozie",
 * "has_children": true,
 * "link": "\/api\/v2\/find\/ey\/72o\/2b91\/7sy"
 */
public class PartCategory {
    private String id;
    private String name;
    private boolean has_children;
    private String link;
    private static final Logger LOGGER = LogManager.getLogger(PartCategory.class);

    public PartCategory(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHas_children() {
        return has_children;
    }

    public void setHas_children(boolean has_children) {
        this.has_children = has_children;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return  name+"(" + id +
                ") ";
    }
}
