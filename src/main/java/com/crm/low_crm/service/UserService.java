package com.crm.low_crm.service;


import com.crm.low_crm.model.enity.User;
import com.crm.low_crm.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepository repository;


    public User findOneUserByChatId(Long chatId){
        return repository.findTopByChatID(chatId);
    }

    public User createUser(Long chatId, org.telegram.telegrambots.meta.api.objects.User userTelegram){
        User user = new User();
        user.setAttempt(10);
        user.setChatID(chatId);
        user.setName((userTelegram.getUserName() != null) ? userTelegram.getUserName() : String.format("%s %s", userTelegram.getLastName(), userTelegram.getFirstName()));
        return saveUser(user);
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    public User setActive(User user){
        user.setActive(true);
        return repository.save(user);
    }

    public List<Long> findAllChatsActiveUsers(){
        return repository.findUsersByActive(true).stream().map(User::getChatID).collect(Collectors.toList());
    }
}
