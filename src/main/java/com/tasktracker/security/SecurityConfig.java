package com.tasktracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.tasktracker.security.AppUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AppUserDetailsService appUserDetailsService;

   public SecurityConfig(AppUserDetailsService appUserDetailsService) {
       this.appUserDetailsService = appUserDetailsService;
   }

   @Bean
   public UserDetailsService userDetailsService() {
       return appUserDetailsService;
   }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(authorize -> authorize
                       .requestMatchers("/tasks/**").authenticated()
                       .requestMatchers("/signin").permitAll()
                       .requestMatchers("/signup").permitAll()
                       .anyRequest().authenticated()
               )
               .formLogin(form -> form
                       .loginPage("/signin")
                       .loginProcessingUrl("/login")
               ).logout(logout -> logout.logoutSuccessUrl("/signin"));

       return http.build();
    }

}
