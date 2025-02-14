package com.financeadvicechatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
    @Value("${ai.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
