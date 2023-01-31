package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.MST_User;
import com.app.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    UserMapper mapper;

    public Iterable<MST_User> findAll() {
        return mapper.findAll();
    }

    public MST_User findOne(String user_address) {
        return mapper.findOne(user_address);
    }

    public void save(MST_User user) {
        mapper.save(user);
    }

    public void update(MST_User user) {
        mapper.update(user);
    }

    public void delete(Integer id) {
        mapper.delete(id);
    }

}
