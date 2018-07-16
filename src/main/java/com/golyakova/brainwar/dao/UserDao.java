package com.golyakova.brainwar.dao;

import com.golyakova.brainwar.models.User;
import java.util.List;

/* data access object - pattern
 *  слой, отвечающий только за доступ к данным
 *  findByEmail() - находим юзера по email из БД,
 *  save() - сохраняем юзера в БД,
 *  findAll() - получам список всех юзеров для вывода рейтинга*/

public interface UserDao {
    User findById(Long id);
    void save(User user);
    void update(User user);
    List<User> findAll();
    Long getId(String email, String password);
    Long getId(String email);
}
