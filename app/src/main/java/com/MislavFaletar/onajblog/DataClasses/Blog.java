package com.MislavFaletar.onajblog.DataClasses;



public class Blog {

    private int id;
    private String title;
    private String content;
    private String img;

    private Blog(){
        //prazan konstruktor potreban za Firebase
    }

    public Blog(int id, String title, String content, String img) {
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
