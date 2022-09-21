package com.dans.data.repo;

import com.dans.data.entity.MdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MdUserRepo extends JpaRepository<MdUser, Long> {

    public MdUser findByUsername(String username);
}
