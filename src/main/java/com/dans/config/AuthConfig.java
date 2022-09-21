package com.dans.config;

import com.dans.service.impl.ClientServiceImplement;
import com.dans.service.impl.UserServiceImplement;
import com.dans.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {

    @Value("{$auth.client-secret:secret}")
    private String clientSecret;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserServiceImplement userDetailsService;

    @Autowired
    private ClientServiceImplement clientService;

    @Bean
    public PasswordEncoder passEncoder() {
        return new EncryptUtil();
    }

    @Bean
    public JwtAccessTokenConverter accessToken() {
        JwtAccessTokenConverter token = new JwtAccessTokenConverter();
        token.setSigningKey(clientSecret);
        return token;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessToken());
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultToken() {
        DefaultTokenServices def = new DefaultTokenServices();
        def.setTokenStore(tokenStore());
        def.setSupportRefreshToken(true);
        return def;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoint) {
        endpoint.authenticationManager(authManager).tokenStore(tokenStore()).accessTokenConverter(accessToken()).userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passEncoder());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.withClientDetails(clientService);
    }
}
