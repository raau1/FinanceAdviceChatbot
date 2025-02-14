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
        // Disable CSRF only for login to prevent Railway blocks
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/login"));

        // Permit public access to login, register, and static assets
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
        );

        // Fix login issues: Ensure login page is accessible & prevent redirect loops
        http.formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true) // 🚀 Redirect to home page after login
                .failureUrl("/login?error=true") // 🚀 Show error message if login fails
                .permitAll()
        );

        // Fix logout handling for Railway: Ensure session is cleared properly
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login") // 🚀 Redirect to login page after logout
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        // Ensure custom user authentication works
        http.userDetailsService(userDetails);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
