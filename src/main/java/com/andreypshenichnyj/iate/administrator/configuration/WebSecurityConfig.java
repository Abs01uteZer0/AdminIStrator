package com.andreypshenichnyj.iate.administrator.configuration;

import com.andreypshenichnyj.iate.administrator.entity.Masters;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    MasterService masterService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService users() {
        List<Masters> list = masterService.getMasters();
        return (UserDetailsService) list;

//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build()
//        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/main").permitAll()
                    .requestMatchers("/management").hasAnyAuthority("ADMIN", "SUPER")
                .anyRequest().authenticated()
                .and().formLogin();
        ;

        return http.build();
    }

}