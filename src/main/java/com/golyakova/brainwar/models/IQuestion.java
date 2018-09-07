package com.golyakova.brainwar.models;

public interface IQuestion {
    long getId();
    String getQuestion();
    String getAnswer1();
    String getAnswer2();
    String getAnswer3();
    String getAnswer4();
    String getTrueanswer();
}
