package com.aviral.whatsappstatussaver.Models;

public class WhatsAppStatusModel {

    private String name;
    private String uri;
    private String path;
    private String fileName;

    public WhatsAppStatusModel(String name, String uri, String path, String fileName) {
        this.name = name;
        this.uri = uri;
        this.path = path;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
