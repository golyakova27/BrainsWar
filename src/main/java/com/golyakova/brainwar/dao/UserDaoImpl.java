package com.golyakova.brainwar.dao;

import com.golyakova.brainwar.models.User;
import com.golyakova.brainwar.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoImpl implements UserDao {

    public User findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public List<User> findAll() {
        return  (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM User ORDER BY points DESC").list();
    }

    @Override
    public Long getId(String gettingEmail, String gettingPassword) {
        String hql = "FROM User where email = :email AND password = :password ";
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(hql);
        query.setParameter("email", gettingEmail);
        query.setParameter("password", gettingPassword);
        List<User> users = query.list();
        if (users.size() > 0) {
            Long id = users.get(0).getId();
            return id;
        }
        else {
            return 0l;
        }
    }

    @Override
    public Long getId(String gettingEmail) {
        String hql = "FROM User where email = :email";
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(hql);
        query.setParameter("email", gettingEmail);
        List<User> users = query.list();
        if (users.size() > 0) {
            Long id = users.get(0).getId();
            return id;
        }
        else {
            return 0l;
        }
    }
}
