package com.golyakova.brainwar.models;

import java.io.Serializable;
import java.util.Random;

public class Rating implements Serializable {
    private String userName;
    private Long points;

    public Rating() {

    }

    public Rating(String userName, Long points) {
        this.userName = userName;
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
