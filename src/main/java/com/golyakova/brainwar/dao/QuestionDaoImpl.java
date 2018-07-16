package com.golyakova.brainwar.dao;

import com.golyakova.brainwar.models.Question;
import com.golyakova.brainwar.models.User;
import com.golyakova.brainwar.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    @Override
    public List<Question> getQuestionList() {
        List<Question> questions = (List<Question>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Question").list();
        return questions;
    }
}
