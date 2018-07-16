package com.golyakova.brainwar.domain.dto.request;

import java.io.Serializable;

public class GameRqDTO implements Serializable {
    private String email;
    private Long newPoints;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNewPoints() {
        return newPoints;
    }

    public void setNewPoints(Long newPoints) {
        this.newPoints = newPoints;
    }
}

