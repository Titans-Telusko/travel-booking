package com.titans.travelbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         return http
                 .csrf(customize->customize.disable())
                 .authorizeHttpRequests(request->request
                         .requestMatchers("/users/register" , "/users/login" , "/admins/register" ,"/admins/login").permitAll()
                         .anyRequest().authenticated())
                 .httpBasic(Customizer.withDefaults())
                 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .build();

     }

     @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
         DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
         provider.setUserDetailsService(userDetailsService);
         provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
         return provider;
     }

     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
         return configuration.getAuthenticationManager();
     }



}
