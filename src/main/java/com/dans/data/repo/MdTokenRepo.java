package com.dans.data.repo;

import com.dans.data.entity.MdToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MdTokenRepo extends JpaRepository<MdToken, Long> {

    public MdToken findByClient(String client);
}
