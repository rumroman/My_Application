package com.example.myapplication.model;

public class CompositionInfo {

    private String compositionName;

    private String authorName;

    public CompositionInfo(String compositionName, String authorName) {
        this.compositionName = compositionName;
        this.authorName = authorName;
    }

    public String getCompositionName() {
        return compositionName;
    }

    public void setCompositionName(String compositionName) {
        this.compositionName = compositionName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
