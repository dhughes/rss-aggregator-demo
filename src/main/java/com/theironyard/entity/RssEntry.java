package com.theironyard.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RssEntry {

    @Id
    private String uri;

    private String title;

    @Column(length = 2000)
    private String imageUrl;

    @Column(length = 2000)
    private String description;

    private String author;

    private Date publishedDate;

    @Column(length = 2000)
    private String linkUrl;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private RssFeed feed;

    public RssEntry() {
    }

    public RssEntry(String uri, String title, String imageUrl, String description, String author, Date publishedDate, String linkUrl, RssFeed feed) {
        this.uri = uri;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.author = author;
        this.publishedDate = publishedDate;
        this.linkUrl = linkUrl;
        this.feed = feed;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public RssFeed getFeed() {
        return feed;
    }

    public void setFeed(RssFeed feed) {
        this.feed = feed;
    }
}
