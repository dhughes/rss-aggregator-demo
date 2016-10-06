package com.theironyard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RssFeed {

    @Id
    private String url;

    private String title;

    @Column(length = 2000)
    private String desciption;

    @Column(length = 2000)
    private String imageUrl;

    @Column(length = 2000)
    private String faviconUrl;

    public RssFeed() {
    }

    public RssFeed(String url, String title, String desciption, String imageUrl, String faviconUrl) {
        this.url = url;
        this.title = title;
        this.desciption = desciption;
        this.imageUrl = imageUrl;
        this.faviconUrl = faviconUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFaviconUrl() {
        return faviconUrl;
    }

    public void setFaviconUrl(String faviconUrl) {
        this.faviconUrl = faviconUrl;
    }
}
