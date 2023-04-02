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

    public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
        return userMapper.getLoginUserInfo(tempLoginUserBean);
    }

    public UserBean getModifyUserInfo(int user_idx) {
        return userMapper.getModifyUserInfo(user_idx);
    }

    public void modifyUserInfo(UserBean modifyUserBean) {
        userMapper.modifyUserInfo(modifyUserBean);
    }
}



