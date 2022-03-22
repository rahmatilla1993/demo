package com.example.demo.config;

import com.example.demo.enums.Permission;
import com.example.demo.enums.RoleName;
import com.example.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/product/**").hasAnyRole(RoleName.USER.name(), RoleName.ADMIN.name())
//                .antMatchers("/api/product/**").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/product/**").hasAuthority(Permission.PRODUCT_GET.getPermission())
                .antMatchers("/api/product/**").hasAuthority(Permission.PRODUCT_EDIT.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
//                .inMemoryAuthentication()
//                .withUser("user").roles(RoleName.USER.name())
//                .authorities(RoleName.USER.getAuthorities())
//                .password(passwordEncoder().encode("user"))
//                .and()
//                .withUser("admin").roles(RoleName.ADMIN.name())
//                .authorities(RoleName.ADMIN.getAuthorities())
//                .password(passwordEncoder().encode("admin"));
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User
//                        .builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .roles(RoleName.USER.name())
//                        .authorities(RoleName.USER.getAuthorities())
//                        .build(),
//
//                User
//                        .builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .roles(RoleName.ADMIN.name())
//                        .authorities(RoleName.ADMIN.getAuthorities())
//                        .build()
//        );
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
