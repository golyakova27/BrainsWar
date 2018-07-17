package com.golyakova.brainwar.domain.dto.request;

import java.io.Serializable;

public class CollaborativeGameRqDTO implements Serializable {

    private String email;
    private String login;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
