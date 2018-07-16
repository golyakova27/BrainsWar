package com.golyakova.brainwar.models;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    private Long id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String trueanswer;

    public Question() {

    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getTrueanswer() {
        return trueanswer;
    }

    @Override
    public String toString() {
        return "question{" +
                question + " \n" +
                answer1 + " \n" +
                answer2 + " \n" +
                answer3 + " \n" +
                answer4 + " \n" +
                trueanswer + "}";
    }
}
