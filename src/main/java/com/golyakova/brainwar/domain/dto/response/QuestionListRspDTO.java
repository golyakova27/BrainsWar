package com.golyakova.brainwar.domain.dto.response;

import com.golyakova.brainwar.models.IQuestion;
import com.golyakova.brainwar.models.Question;

import java.io.Serializable;
import java.util.List;

public class QuestionListRspDTO implements Serializable{
    private List<IQuestion> questions;

    public QuestionListRspDTO() {

    }

    public QuestionListRspDTO(List<IQuestion> questions) {
        this.questions = questions;
    }

    public List<IQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<IQuestion> questions) {
        this.questions = questions;
    }
}
