package com.financeadvicechatbot.controller;

import com.financeadvicechatbot.dto.ChatbotInfoDto;
import com.financeadvicechatbot.model.User;
import com.financeadvicechatbot.repository.UserRepository;
import com.financeadvicechatbot.service.OpenAiService;
import com.financeadvicechatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private OpenAiService openAiService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() { return "index";}

    @RequestMapping("/financeAdvice")
    public String financeAdvise(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        System.out.println("Entering this function");
        User user = userRepository.findByEmail(userDetails.getUsername());
        ChatbotInfoDto chatbotInfo = userService.getChatbotInfo(user);
        List<String> chatbotOutput = openAiService.getChatbotResponse(user, chatbotInfo);
        model.addAttribute("chatbotOutput2", chatbotOutput.get(1));
        model.addAttribute("chatbotOutput3", chatbotOutput.get(2));
        model.addAttribute("chatbotOutput4", chatbotOutput.get(3));
        model.addAttribute("chatbotOutput5", chatbotOutput.get(4));
        model.addAttribute("chatbotOutput6", chatbotOutput.get(5));
        return "financeAdvise";
    }

    @GetMapping("/chatbot")
    public String chatbot(){ return "Chatbot";}

    @PostMapping("/chatbot")
    public String chatbot(@AuthenticationPrincipal UserDetails userDetails, @RequestParam float monthlyIncome, @RequestParam String financialAim, @RequestParam float monthlySaving, @RequestParam float necessities, @RequestParam float leisureMiscellaneous, @RequestParam float subscriptions) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        user.setMonthlyIncome(monthlyIncome);
        user.setFinancialAim(financialAim);
        user.setMonthlySavings(monthlySaving);
        user.setSpendingNeccesities(necessities);
        user.setSpendingLeisure(leisureMiscellaneous);
        user.setSpendingSubscriptions(subscriptions);
        userRepository.save(user);
        return "redirect:/financeAdvice";
    }
}
