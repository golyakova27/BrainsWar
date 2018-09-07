package com.golyakova.brainwar.services;

import com.golyakova.brainwar.models.IQuestion;
import com.golyakova.brainwar.models.Question;

import java.util.List;

public interface QuestionService {
    List<IQuestion> getQuestions(String theme);
}
