package com.golyakova.brainwar.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.golyakova.brainwar.domain.dto.request.LoginRqDTO;
import com.golyakova.brainwar.domain.dto.request.SignUpRqDTO;
import com.golyakova.brainwar.domain.dto.response.LoginRspDTO;
import com.golyakova.brainwar.models.User;
import com.golyakova.brainwar.services.UserService;
import com.golyakova.brainwar.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @RequestMapping(value = "/v1/login", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<LoginRspDTO> checkLoginAndPassword(@RequestBody LoginRqDTO user) {

        UserService userService = new UserServiceImpl();
        User u = userService.getUser(user.getEmail(), user.getPassword());

        if (u != null) {

            LoginRspDTO response = new LoginRspDTO();
            response.setContained(true);
            response.setNickname(u.getLogin());
            response.setPoints(u.getPoints());
            response.setRatings(userService.getRatings());

            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK);
        } else {
            LoginRspDTO response = new LoginRspDTO();
            response.setContained(false);
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/v1/signUp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> signUpNewUser(@RequestBody SignUpRqDTO newUser) {
        UserService userService = new UserServiceImpl();

        User user = new User(newUser.getEmail(), newUser.getNickname(), newUser.getPassword());
        System.out.println(user);
        Boolean response = userService.saveUser(user);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}