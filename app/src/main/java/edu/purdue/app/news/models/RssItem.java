package edu.purdue.app.news.models;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 *
 * @author hughe127
 *
 */
public class RssItem {

    private String title;
    private String link;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title;
    }

}