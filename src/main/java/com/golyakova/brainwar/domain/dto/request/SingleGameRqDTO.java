package com.golyakova.brainwar.domain.dto.request;

import java.io.Serializable;

public class SingleGameRqDTO implements Serializable {
    private String theme;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
