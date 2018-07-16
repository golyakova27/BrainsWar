package com.golyakova.brainwar.models;

import javax.persistence.*;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private Long points = 0l;

    public User() {
    }

    public User(String email, String login, String password) {
        this.email = email;
        this.nickname = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return nickname;
    }

    public void setLogin(String login) {
        this.nickname = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPoints() {
        return points;
    }

    public void addPoints(Long points) {
        this.points += points;
    }

    @Override
    public String toString() {
        return "user{" +
                email + " " +
                nickname + " " +
                password + " " +
                points +
                "}";
    }
}
