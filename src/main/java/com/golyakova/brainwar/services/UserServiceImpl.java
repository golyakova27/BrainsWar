package com.golyakova.brainwar.services;

import com.golyakova.brainwar.dao.UserDao;
import com.golyakova.brainwar.dao.UserDaoImpl;
import com.golyakova.brainwar.domain.dto.request.LoginRqDTO;
import com.golyakova.brainwar.models.Rating;
import com.golyakova.brainwar.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.LongToIntFunction;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    public UserServiceImpl() {

    }

    @Override
    public Boolean saveUser(User user) {
        Long id = userDao.getId(user.getEmail());
        if (id == 0l) {
            userDao.save(user);
            return true;
        } else {
            return false;
        }
    }

    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User getUser(String email, String password) {
        Long id = userDao.getId(email, password);
        if (id != 0l) {
            User user = userDao.findById(id);
            return user;
        }
        else {
            return null;
        }
    }

    @Override
    public List<Rating> getRatings() {
        List<User> users = userDao.findAll();

        List<Rating> ratings = new ArrayList<>();
        for (int i = 0; i < users.size() && i < 6; i++) {
            Rating rating = new Rating(users.get(i).getLogin(), users.get(i).getPoints());
            ratings.add(rating);
        }
        return ratings;
    }

    @Override
    public User update(String email, Long points) {
        Long id = userDao.getId(email);
        if (id != 0l) {
            User user = userDao.findById(id);
            user.addPoints(points);
            update(user);
            return user;
        } else {
            return null;
        }
    }
}
