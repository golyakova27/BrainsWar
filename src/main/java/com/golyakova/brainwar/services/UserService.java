package com.golyakova.brainwar.services;

/* service слой, отвечающий за выполнение бизнес-логики*/

import com.golyakova.brainwar.domain.dto.request.LoginRqDTO;
import com.golyakova.brainwar.models.Rating;
import com.golyakova.brainwar.models.User;

import java.util.List;

public interface UserService {
    Boolean saveUser(User user);
    User getUser(String email, String password);
    List<Rating> getRatings ();
    User update(String email, Long points);
}
