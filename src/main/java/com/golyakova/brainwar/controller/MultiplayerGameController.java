package com.golyakova.brainwar.controller;

import com.golyakova.brainwar.domain.dto.request.CollaborativeGameRqDTO;
import com.golyakova.brainwar.domain.dto.request.LoginRqDTO;
import com.golyakova.brainwar.domain.dto.request.SingleGameRqDTO;
import com.golyakova.brainwar.domain.dto.response.LoginRspDTO;
import com.golyakova.brainwar.domain.dto.response.QuestionListRspDTO;
import com.golyakova.brainwar.services.QuestionService;
import com.golyakova.brainwar.services.QuestionServiceImpl;
import javafx.util.Pair;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MultiplayerGameController {

    private List<String> usersForMultiplay = new ArrayList<>();
    private List<Pair<String, String>> rooms = new ArrayList<>();


    @MessageMapping("/questions")
    public void createRoom(CollaborativeGameRqDTO user, SingleGameRqDTO theme) throws Exception {

        usersForMultiplay.add(user.getEmail());
        int userCount = usersForMultiplay.size();
        System.out.println(user.getEmail() + " " + user.getLogin());
        if (userCount % 2 == 0 && userCount != 0) {
            rooms.add(new Pair<String, String>(usersForMultiplay.get(userCount - 1),
                                                usersForMultiplay.get(userCount - 2)));
        }

    }

    @SendTo("/topic/response")
    public QuestionListRspDTO sendQuestions(SingleGameRqDTO theme) throws Exception {

        QuestionService questionService = new QuestionServiceImpl();
        return new QuestionListRspDTO(questionService.getQuestions(theme.getTheme()));

    }

    @MessageMapping("/take")
    @SendTo("/topic/response")
    public LoginRspDTO greeting(LoginRqDTO user) throws Exception {

        QuestionService questionService = new QuestionServiceImpl();
        LoginRspDTO response = new LoginRspDTO();
        return response;
    }

}
