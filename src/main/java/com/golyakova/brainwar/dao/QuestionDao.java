package com.golyakova.brainwar.dao;

import com.golyakova.brainwar.models.Question;

import java.util.List;

/* data access object - pattern
 *  слой, отвечающий только за доступ к данным
 *  findById() - находим вопрос по его id в БД*/

public interface QuestionDao {
    List<Question> getQuestionList();
}
