package com.example.myapplication.model;

public class CompositionInfo {

    private int compositionId;

    private String compositionName;

    private String authorName;

    public CompositionInfo(int compositionId, String compositionName, String authorName) {
        this.compositionId = compositionId;
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

    public int getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(int compositionId) {
        this.compositionId = compositionId;
    }
}
