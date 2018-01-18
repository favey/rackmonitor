package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserName(String userName);

}
