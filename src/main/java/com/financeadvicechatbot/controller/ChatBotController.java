package com.financeadvicechatbot.controller;

import com.financeadvicechatbot.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatBotController {

    private final OpenAiService openAiService;

    public ChatBotController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    /**
     @PostMapping
     public String chatWithBot(@RequestBody Map<String, String> request) {
     String userInput = request.get("message");
     return openAiService.getChatbotResponse(userInput);
     }
     ***/

}
