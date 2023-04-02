package com.example.dao;

import com.example.beans.UserBean;
import com.example.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final UserMapper userMapper;

    public String checkUserIdExist(String user_id) {
        return userMapper.checkUserIdExist(user_id);
    }

    public void addUserInfo(UserBean joinUserBean) {
        userMapper.addUserInfo(joinUserBean);
    }
}


