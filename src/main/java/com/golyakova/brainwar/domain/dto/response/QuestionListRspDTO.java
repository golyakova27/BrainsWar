package com.golyakova.brainwar.domain.dto.response;

import com.golyakova.brainwar.models.Question;

import java.io.Serializable;
import java.util.List;

public class QuestionListRspDTO implements Serializable{
    private List<Question> questions;

    public QuestionListRspDTO() {

    }

    public QuestionListRspDTO(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
