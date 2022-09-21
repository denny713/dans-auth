package com.dans.service.core;

import com.dans.data.entity.MdUser;

import javax.transaction.Transactional;

@Transactional
public interface UserService {

    public MdUser getByUsername(String username);
}
