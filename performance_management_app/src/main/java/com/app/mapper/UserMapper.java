package com.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.app.domain.MST_User;

@Mapper
public interface UserMapper {
    Iterable<MST_User> findAll();

    MST_User findOne(String user_address);

    void save(MST_User user);

    void update(MST_User user);

    void delete(Integer id);

}
