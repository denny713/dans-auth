package com.dans.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("{$auth.client-secret:secret}")
    private String secret;

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(secret);
        return converter;
    }

    @Bean
    public TokenStore token() {
        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    public DefaultTokenServices tokenDefault() {
        DefaultTokenServices defToken = new DefaultTokenServices();
        defToken.setTokenStore(token());
        return defToken;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer security) {
        security.tokenServices(tokenDefault());
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();
    }
}
