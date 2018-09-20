package com.oms.order.webservice.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class NbtyWebServiceConfigurerAdapter extends WebSecurityConfigurerAdapter
{
    @Autowired
    private NbtyAuthenticationEntryPoint authEntryPoint;

    @Value("${oms.webservice.authorization.username}")
    private String wsAuthorizationUsername;

    @Value("${oms.webservice.authorization.password}")
    private String wsAuthorizationPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic()
                .authenticationEntryPoint(authEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser(wsAuthorizationUsername).password(wsAuthorizationPassword).roles("USER");

    }
}
