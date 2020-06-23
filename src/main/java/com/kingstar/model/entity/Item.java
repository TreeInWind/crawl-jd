package com.kingstar.model.entity;

import java.io.Serializable;

public class Item implements Serializable {
    private Long id;

    private String title;

    private String url;

    private static final long serialVersionUID = 1L;

    public Item(long id,String title,String url){
        super();
        this.id = id;
        this.title = title;
        this.url = url;
    };

    public Item(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}