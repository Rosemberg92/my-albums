package com.applications.albums.myalbums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.applications.albums.myalbums.config.CustomAccessDeniedHandler;
import com.applications.albums.myalbums.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] PUBLIC_MATCHERS = {
            "/",
            "/error",
            "/forbidden",
            "/layouts",
            "/login",
            "/user/register",
            "/user/save",
            "/uploads/**",
            "/js/**",
            "/css/**",
            "/albums/see-comments/**",
            "/load-album"
    };

    public static final String LOGIN_PAGE = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_PROCESSING = "/signin";
    public static final String LOGIN_FAIL_URL = LOGIN_PAGE + "?error=true";
    public static final String DEFAULT_SUCCESS_URL = "/";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

   @Bean
   public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(auth -> {
                auth.requestMatchers(PUBLIC_MATCHERS).permitAll();
                auth.anyRequest().authenticated();
            })
            .formLogin(form -> {
                form.loginProcessingUrl(LOGIN_PROCESSING)
                    .loginPage(LOGIN_PAGE)
                    .permitAll()
                    .defaultSuccessUrl(DEFAULT_SUCCESS_URL)
                    .failureUrl(LOGIN_FAIL_URL)
                    .usernameParameter(USERNAME)
                    .passwordParameter(PASSWORD);
            })
            .build();
   }
    
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }*/
      

}