package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.TerminalMenu;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by arek50 on 2017-04-09.
 */
public class BreadcrumbsBuilder {
    private String link;
    private String title;
    private static final Logger LOGGER = LogManager.getLogger(BreadcrumbsBuilder.class);

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
