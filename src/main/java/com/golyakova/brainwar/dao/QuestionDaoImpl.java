package com.golyakova.brainwar.dao;

import com.golyakova.brainwar.models.IQuestion;
import com.golyakova.brainwar.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    @Override
    public List<IQuestion> getQuestionList(String theme) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<IQuestion> questions;
        switch (theme) {
            case "sport":
                return (List<IQuestion>) session.createQuery("FROM Question").list();
            case "music":
                return (List<IQuestion>) session.createQuery("FROM Questionmusic").list();
            case "art":
                return (List<IQuestion>) session.createQuery("FROM Questionart").list();
            case "nature":
                return (List<IQuestion>) session.createQuery("FROM Questionnature").list();
        }

        return null;
    }
}
