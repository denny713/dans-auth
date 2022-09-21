package com.dans.service.impl;

import com.dans.data.entity.MdUser;
import com.dans.service.core.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement extends BaseService implements UserService, UserDetailsService {

    @Override
    public MdUser getByUsername(String username) {
        return mdUserRepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return mdUserRepo.findByUsername(s);
    }
}
