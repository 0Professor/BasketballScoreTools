package com.example.basketballscoretools.ui.notifications;

public class Bean {
    private String name;
    private int imageid;

    public Bean(String name, int imageid) {
        this.name = name;
        this.imageid = imageid;
    }

    public String getName(){
        return name;
    }
    public int getImageid() {
        return imageid;
    }
}
