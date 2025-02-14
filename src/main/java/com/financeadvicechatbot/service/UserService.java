package com.financeadvicechatbot.service;

import com.financeadvicechatbot.dto.ChatbotInfoDto;
import com.financeadvicechatbot.model.User;
import com.financeadvicechatbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    // Making the userService
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Finding a user by their email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Function to register a new user to the site
    public User registerUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    //Function to get all the user's information that would be used in the Chatbot
    public ChatbotInfoDto getChatbotInfo(User user){
        ChatbotInfoDto chatbotInfoDto = new ChatbotInfoDto();
        chatbotInfoDto.setFinancialAim(user.getFinancialAim());
        chatbotInfoDto.setMonthlyIncome(user.getMonthlyIncome());
        chatbotInfoDto.setMonthlySavings(user.getMonthlySavings());
        chatbotInfoDto.setSpendingNeccesities(user.getSpendingNeccesities());
        chatbotInfoDto.setSpendingLeisure(user.getSpendingLeisure());
        chatbotInfoDto.setSpendingSubscriptions(user.getSpendingSubscriptions());
        chatbotInfoDto.setSpendingOther(user.getSpendingOther());
        return chatbotInfoDto;
    }
}
