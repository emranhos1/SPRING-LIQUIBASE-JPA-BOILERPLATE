package com.eh.gc.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 17 MAR, 2023
 * @version 1.1
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${springdoc.swagger-ui.path}")
    private String swaggerUiPath;

    @Value("${springdoc.api-docs.path}")
    private String swaggerDocPath;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final SimpleCrosFilter simpleCorsFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler, SimpleCrosFilter simpleCorsFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.simpleCorsFilter = simpleCorsFilter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                    .disable()
                // handle an authorized attempts
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Add our custom JWT security filter
                //.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers("/test")
                .permitAll()
                .antMatchers(swaggerDocPath+"/**",swaggerUiPath+"/**", "/swagger-ui/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        /* TODO: jwtAuthenticationFilter() is incomplete. can not verify the token */
        http.addFilterBefore( simpleCorsFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
