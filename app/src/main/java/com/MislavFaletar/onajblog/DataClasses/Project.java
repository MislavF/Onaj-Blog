package com.MislavFaletar.onajblog.DataClasses;



public class Project {

    private int id;
    private String title;
    private String content;
    private String img;

    private Project(){
        //prazan konstruktor potreban za Firebase
    }

    public Project(int id, String title, String content, String img) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }
}
