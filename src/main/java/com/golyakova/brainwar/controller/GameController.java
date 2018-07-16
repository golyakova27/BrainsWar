package com.golyakova.brainwar.controller;

import com.golyakova.brainwar.domain.dto.request.LoginRqDTO;
import com.golyakova.brainwar.domain.dto.response.LoginRspDTO;
import com.golyakova.brainwar.services.UserService;
import com.golyakova.brainwar.services.UserServiceImpl;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @MessageMapping("/answer")
    @SendTo("/topic/response")
    public LoginRspDTO greeting(LoginRqDTO user) throws Exception {

        LoginRspDTO response = new LoginRspDTO();

        UserService userService = new UserServiceImpl();
        //response.setResponse(userService.isContainedUser(user.getEmail(), user.getPassword()));
        return response;
    }
}
