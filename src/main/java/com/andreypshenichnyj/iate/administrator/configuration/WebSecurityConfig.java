package com.andreypshenichnyj.iate.administrator.configuration;

import com.andreypshenichnyj.iate.administrator.entity.Masters.Masters;
import com.andreypshenichnyj.iate.administrator.entity.Masters.Permission;
import com.andreypshenichnyj.iate.administrator.entity.Masters.Role;
import com.andreypshenichnyj.iate.administrator.security.UserDetailServiceImpl;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    protected UserDetailsService users() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password("admin")
//                        .authorities(Role.ADMIN.getAuthorities())       //С помощью прав сета прав
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password("user")
//                        .roles(Role.TEACHER.name())     //С помощью роли
//                        .build()
//        );
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/main").hasAuthority(Permission.ADMINS_READ.getPermission())
                    .requestMatchers("/management").hasAuthority(Permission.ADMINS_READ.getPermission())
                    .requestMatchers("/administration").hasAuthority(Permission.ADMINS_READ.getPermission())        //С помощью permission
                .anyRequest().authenticated()
                .and().formLogin();

        return http.build();
    }
}