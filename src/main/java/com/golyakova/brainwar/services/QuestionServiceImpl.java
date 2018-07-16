package com.golyakova.brainwar.services;

import com.golyakova.brainwar.dao.QuestionDao;
import com.golyakova.brainwar.dao.QuestionDaoImpl;
import com.golyakova.brainwar.models.Question;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService{

    private QuestionDao questionDao = new QuestionDaoImpl();

    @Override
    public List<Question> getQuestions() {

        List<Question> questions = questionDao.getQuestionList();

        List<Integer> count = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Boolean flag = false;
            Integer newC = (int)(Math.random() * 26);
            for (Integer c : count) {
                if (newC.equals(c)) flag = true;
            }
            if (!flag) {
                count.add(newC);
            } else {
                i--;
            }
        }

        List<Question> que = new ArrayList<>();
        for (Integer counter : count) {
            que.add(questions.get(counter));
        }

        return que;
    }
}
