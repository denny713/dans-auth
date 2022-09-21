package com.dans.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class ClientServiceImplement extends BaseService implements ClientDetailsService {

    @Override
    @Cacheable("jwtToken")
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return mdTokenRepo.findByClient(s);
    }
}
