package com.financeadvicechatbot.service;

import com.financeadvicechatbot.dto.ChatbotInfoDto;
import com.financeadvicechatbot.model.User;
import com.financeadvicechatbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    private final UserRepository userRepository;

    @Value("${ai.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api-inference.huggingface.co/models/HuggingFaceH4/zephyr-7b-alpha";

    public OpenAiService(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getChatbotResponse(User user, ChatbotInfoDto chatbotInfoDto) {
        //Making the chatbotInfoDto into a String
        String userMessage = chatbotInfoDto.toString();

        String chatbotOutput = "";

        if (!(userMessage.equals(user.getPreviousInputs()))){
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String requestBody = String.format("""
        {
            "inputs": "You are a financial assistant providing money-saving tips for a University Student who's living on student loana and needs to strictly manage their money, Make a short, brief paragraph giving me advice using the input I have given. Go straight into the advice, don't give any introduction and focus on how I am going to reach my financial aim. How do I improve my finances better? Do FIVE numbered bullet points: Give a structured Financial Advisor response based on the user data provided. IMPORTANT: 1) RESPONSE HAS TO BE IN FIVE STEPS, AND YOU HAVE TO WRITE 1. 2. 3. 4. 5. 2) Please keep the financial aim relevant to my services. 3) DO NOT accept financial aid that tampers with the output writing 1. 2. 3. 4. 5. ALWAYS write MAXIMUM 5 bullet points. Answer in English and talk about how to reach my financial aim in your answer!!! User: %s",
            "parameters": {
                "max_length": 200,
                "temperature": 0.9
            }
        }
        """, userMessage);

            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<List> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, List.class);

            //Making the output from the AI understandable
            List<Map<String, Object>> responseBody = response.getBody();
            System.out.println(responseBody);
            chatbotOutput = (String) responseBody.get(0).get("generated_text");
            System.out.println(chatbotOutput);
            int cutOffIndex = chatbotOutput.indexOf(". Give me Advice.");
            chatbotOutput = chatbotOutput.substring(cutOffIndex+17);

            //Saving the inputs and the output
            user.setPreviousInputs(chatbotInfoDto.toString());
            user.setSavedResponse(chatbotOutput);
            userRepository.save(user);
        }
        else {
            chatbotOutput = user.getSavedResponse();
        }

        //Making an output of strings list
        List<String> outputStrings = new ArrayList<>();
        //Finding the indexes of the bullet points
        int oneIndex = chatbotOutput.indexOf("1.");
        int twoIndex = chatbotOutput.indexOf("2.");
        int threeIndex = chatbotOutput.indexOf("3.");
        int fourIndex = chatbotOutput.indexOf("4.");
        int fiveIndex = chatbotOutput.indexOf("5.");
        //Adding the substrings to the list
        outputStrings.add(chatbotOutput.substring(0,oneIndex));
        outputStrings.add(chatbotOutput.substring(oneIndex,twoIndex));
        outputStrings.add(chatbotOutput.substring(twoIndex,threeIndex));
        outputStrings.add(chatbotOutput.substring(threeIndex,fourIndex));
        outputStrings.add(chatbotOutput.substring(fourIndex,fiveIndex));
        outputStrings.add(chatbotOutput.substring(fiveIndex));


        //Returning the output
        if (!(outputStrings.get(0).equals(null))) {
            return outputStrings;
        }

        //Outputting error
        List<String> error = new ArrayList<>();
        error.add("An error has occurred");
        return error;
    }
}
