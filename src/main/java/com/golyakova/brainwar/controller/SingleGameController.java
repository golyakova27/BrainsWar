package com.golyakova.brainwar.controller;

import com.golyakova.brainwar.domain.dto.request.GameRqDTO;
import com.golyakova.brainwar.domain.dto.request.LoginRqDTO;
import com.golyakova.brainwar.domain.dto.request.SingleGameRqDTO;
import com.golyakova.brainwar.domain.dto.response.LoginRspDTO;
import com.golyakova.brainwar.domain.dto.response.QuestionListRspDTO;
import com.golyakova.brainwar.models.User;
import com.golyakova.brainwar.services.QuestionService;
import com.golyakova.brainwar.services.QuestionServiceImpl;
import com.golyakova.brainwar.services.UserService;
import com.golyakova.brainwar.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SingleGameController {

    @RequestMapping(value = "/single/game", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<QuestionListRspDTO> getQuestions(@RequestBody SingleGameRqDTO theme) {
        QuestionService questionService = new QuestionServiceImpl();
        QuestionListRspDTO response = new QuestionListRspDTO(questionService.getQuestions());

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/single/profile", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<LoginRspDTO> updateUserProfile(@RequestBody GameRqDTO gameInfo) {
        UserService userService = new UserServiceImpl();
        LoginRspDTO response = new LoginRspDTO();
        User user = userService.update(gameInfo.getEmail(), gameInfo.getNewPoints());
        if (user != null) {
            response.setContained(true);
            response.setNickname(user.getLogin());
            response.setPoints(user.getPoints());
            response.setRatings(userService.getRatings());
            return new ResponseEntity<LoginRspDTO>(
                    response,
                    HttpStatus.OK
            );
        } else {
            response.setContained(false);
            return new ResponseEntity<LoginRspDTO>(
                    response,
                    HttpStatus.OK
            );
        }
    }
}
