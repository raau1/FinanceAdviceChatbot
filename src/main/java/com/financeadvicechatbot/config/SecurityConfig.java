package com.financeadvicechatbot.config;

import com.financeadvicechatbot.service.UserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetails userDetails;

    public SecurityConfig(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        http.userDetailsService(userDetails);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
