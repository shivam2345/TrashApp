package com.example.shiva.splashscreen;

public class items {
    boolean isItTrash;
    int speed;
    int imageID;
    int x;
    int y;

    public items(boolean isItTrashOrNot, int newspeed, int imageid, int x, int y) {
        isItTrash = isItTrashOrNot;
        speed = newspeed;
        imageID = imageid;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
