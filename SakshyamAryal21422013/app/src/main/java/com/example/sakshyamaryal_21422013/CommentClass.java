package com.example.sakshyamaryal_21422013;

public class CommentClass {

    private int id;
    private String post;
    private String name;
    private String date;
    //class to store the data with constructor and setter and getter
    @Override
    public String toString() {
        return "CommentClass{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public CommentClass(int id, String post, String name, String date) {
        this.id = id;
        this.post = post;
        this.name = name;
        this.date = date;
    }

    // getter and setter method

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}



