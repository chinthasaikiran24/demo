package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();

    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//
//        http
//        .csrf(csrf -> csrf.disable())  
//
//                .authorizeHttpRequests(auth -> auth
//				
//				 .requestMatchers(HttpMethod.GET, "/employees/*").authenticated()
//
//                        .requestMatchers("/register")
//                        .permitAll()
//						
//						
//
//                        .anyRequest()
//
//                        .authenticated())
//
//                
//        .formLogin(form -> form.disable())
//        .httpBasic(httpBasic -> httpBasic.disable());
//
//        return http.build();
//
//    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .formLogin(form -> form.disable())
            .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }

}