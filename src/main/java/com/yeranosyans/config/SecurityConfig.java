package com.yeranosyans.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${auth0.apiAudience}")
    private String apiAudience;

    @Value("${auth0.issuer}")
    private String issuer;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer.forRS256(apiAudience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/server/api/v1/**").permitAll()
                .antMatchers(HttpMethod.GET, "/server/api/v1/bikes").hasAnyAuthority("view:registrations")
                .antMatchers(HttpMethod.GET, "/server/api/v1/bikes/**").hasAnyAuthority("view:registration")
                .antMatchers(HttpMethod.GET, "/health", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/v2/**").permitAll()
                .anyRequest().authenticated();
    }
}
