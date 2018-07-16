package com.golyakova.brainwar.domain.dto.response;

import com.golyakova.brainwar.models.Rating;

import java.io.Serializable;
import java.util.List;

public class LoginRspDTO implements Serializable {

    private Boolean isContained;
    private String nickname;
    private Long points;
    private List<Rating> ratings;

    public Boolean getContained() {
        return isContained;
    }

    public void setContained(Boolean contained) {
        isContained = contained;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
