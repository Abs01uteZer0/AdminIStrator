package com.andreypshenichnyj.iate.administrator.configuration;

import com.andreypshenichnyj.iate.administrator.entity.Masters.Permission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/management/main/add-student").hasAuthority(Permission.TEACHERS_WRITE.getPermission())
                    .requestMatchers("/management/main/*").hasAuthority(Permission.ADMINS_READ.getPermission())
                    .requestMatchers("/management/main/add-admin").hasAuthority(Permission.SUPERUSERS_WRITE.getPermission())
                    .requestMatchers("/management/recovery**").hasAuthority(Permission.ADMINS_READ.getPermission())
                    .requestMatchers("/monitoring").hasAuthority(Permission.ADMINS_READ.getPermission())
                .anyRequest().authenticated()
                .and().formLogin()
                .defaultSuccessUrl("/main")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }
}