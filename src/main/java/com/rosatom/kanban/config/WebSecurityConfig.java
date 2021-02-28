package com.rosatom.kanban.config;

import com.rosatom.kanban.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests() //Authorising all server requests
                    .antMatchers("/static/**").permitAll() //except these
                    .anyRequest().authenticated() //All others must be authorised
                .and()
                    .formLogin()//Showing that we have our own login form
                    .loginPage("/login")//By this mapping
                    .permitAll()//All can use it
                .and()
                    .logout() //Logout can be used by anyone
                    .permitAll();
//              .and()
//                    .rememberMe(); //For not to enter the login and password
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance()); //Loading users with UserService
    }
}
