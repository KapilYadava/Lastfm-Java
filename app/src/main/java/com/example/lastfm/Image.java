package com.example.lastfm;

import java.io.Serializable;

public class Image implements Serializable {
    String text;
    String size;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
