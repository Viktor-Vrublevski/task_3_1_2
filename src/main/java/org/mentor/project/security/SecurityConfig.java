package org.mentor.project.security;

import org.mentor.project.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private LoginSuccessHandler successHandler;

    @Autowired
    public void setSuccessHandler(LoginSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/*","/user").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/registration")
                .permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successHandler(successHandler)
                .permitAll().and()
                .logout()
                .permitAll().logoutSuccessUrl("/login");

    }

}

