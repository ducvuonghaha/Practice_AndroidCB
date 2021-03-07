package com.dcvg.practice.model;

public class Data {
    private String content, status;
    private int id;

    public Data(String content, String status) {
        this.content = content;
        this.status = status;
    }

    public Data(String content, String status, int id) {
        this.content = content;
        this.status = status;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
