package com.meb.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.httpBasic().disable();
        http .csrf().disable() .authorizeRequests() .anyRequest().permitAll();*/


        // http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/events/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/sessions/**").permitAll();
        //http.authorizeRequests().antMatchers(HttpMethod.POST, "/events/**").permitAll();
        //http.authorizeRequests().antMatchers(HttpMethod.POST, "/saveEvent/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/photoProduct/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/photosProduct/**").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/events/**").hasAnyAuthority("USER");


        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(new JWTAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
