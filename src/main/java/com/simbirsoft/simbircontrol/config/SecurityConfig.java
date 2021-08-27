package com.simbirsoft.simbircontrol.config;

import com.simbirsoft.simbircontrol.enums.Role;
import com.simbirsoft.simbircontrol.security.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //https://stackoverflow.com/questions/30819337/multiple-antmatchers-in-spring-security/30819556#30819556
                .antMatchers(HttpMethod.GET, "/project/**").hasAnyAuthority(Role.ADMIN.name(), Role.LEADER.name(), Role.PERFORMER.name(), Role.AUTHOR.name())
                .antMatchers(HttpMethod.PUT, "/project/**").hasAnyAuthority(Role.ADMIN.name(), Role.LEADER.name())
                .antMatchers(HttpMethod.POST, "/project/**").hasAnyAuthority(Role.ADMIN.name(), Role.LEADER.name())
                .antMatchers(HttpMethod.DELETE, "/project/**").hasAnyAuthority(Role.ADMIN.name(), Role.LEADER.name())
                //.antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

}
