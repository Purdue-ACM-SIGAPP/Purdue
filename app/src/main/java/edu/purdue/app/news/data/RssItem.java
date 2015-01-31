package edu.purdue.app.news.data;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 *
 * @author hughe127
 *
 */
public class RssItem {

    // item title
    private String title;
    // item link
    private String link;
    //item description
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

    @Override
    public String toString() {
        return title;
    }

}