package com.andreypshenichnyj.iate.administrator.configuration;

import com.andreypshenichnyj.iate.administrator.entity.Masters;
import com.andreypshenichnyj.iate.administrator.service.MasterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("AuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService users;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Masters master = (Masters) users.loadUserByUsername(username);

        if (master == null || !master.getUsername().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(passwordEncoder.encode(master.getPassword()))) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = master.getAuthorities();

        return new UsernamePasswordAuthenticationToken(master, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
