package com.dans.service.impl;

import com.dans.data.repo.MdTokenRepo;
import com.dans.data.repo.MdUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    protected MdUserRepo mdUserRepo;

    @Autowired
    protected MdTokenRepo mdTokenRepo;
}
