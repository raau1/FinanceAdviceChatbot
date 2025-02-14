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
    // Making the SecurityConfig object, with userDetails
    private final UserDetails userDetails;

    public SecurityConfig(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Any Security configurations go here
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(request -> request.requestMatchers("/register", "/login",
                        "/css/**", "/js/**", "/images/**", "/").permitAll()
                // Any other request has to be authenticated
                .anyRequest().authenticated());
        http.formLogin(form -> form
                .loginPage("/login")
                // Specifying we are using email not username
                .usernameParameter("email")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll());

        // Specify what happens when a user logs out
        http.logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll());
        // Add custom userDetailsService for user authentication
        http.userDetailsService(userDetails);
        // Returning these configurations
        return http.build();
    }

    // Creating a PasswordEncoder, to encode passwords on the database
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
