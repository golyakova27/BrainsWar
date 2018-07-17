package com.golyakova.brainwar.controller;

import com.golyakova.brainwar.domain.dto.request.CollaborativeGameRqDTO;
import com.golyakova.brainwar.domain.dto.request.LoginRqDTO;
import com.golyakova.brainwar.domain.dto.response.LoginRspDTO;
import com.golyakova.brainwar.domain.dto.response.QuestionListRspDTO;
import com.golyakova.brainwar.models.User;
import com.golyakova.brainwar.services.QuestionService;
import com.golyakova.brainwar.services.QuestionServiceImpl;
import com.golyakova.brainwar.services.UserService;
import com.golyakova.brainwar.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/questions")
    @SendTo("/topic/response")
    public QuestionListRspDTO sendQuestions(CollaborativeGameRqDTO user) throws Exception {

        QuestionService questionService = new QuestionServiceImpl();
        return new QuestionListRspDTO(questionService.getQuestions());

    }

    @MessageMapping("/take")
    @SendTo("/topic/response")
    public LoginRspDTO greeting(LoginRqDTO user) throws Exception {

        QuestionService questionService = new QuestionServiceImpl();
        LoginRspDTO response = new LoginRspDTO();
        return response;
    }

    //вот это каждую секунду стучит к клиенту
    @Scheduled(fixedDelay = 1000)
    private void bgColor(){
        simpMessagingTemplate.convertAndSend("/topic/response", new LoginRspDTO());
    }
}
