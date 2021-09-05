package com.transaction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// Create 2 users for demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles("USER", "ADMIN");

	}

	// Secure the endpoins with HTTP Basic authentication
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/transactions/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/transactions").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/transactions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/transactions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/transactions/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }

}
