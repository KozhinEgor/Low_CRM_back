package com.crm.low_crm.repository;

import com.crm.low_crm.model.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface UserRepository extends JpaRepository<User,Long> {
    User findTopByChatID(Long chatId);

    List<User> findUsersByActive(boolean active);
}
