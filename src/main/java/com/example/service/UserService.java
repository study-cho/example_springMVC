package com.example.service;

import com.example.beans.UserBean;
import com.example.entity.User;
import com.example.mapstruct.UserMapper;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository users;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    public boolean checkUserIdExist(String user_id) {
        User findUser = users.findByUserId(user_id);

        return findUser == null ? true: false;
    }

    public void addUserInfo(UserBean joinUserBean) {
        User user = UserMapper.INSTANCE.toUser(joinUserBean);
        users.save(user);
    }

    public void getLoginUserInfo(UserBean tempLoginUserBean) {

        User findUser = users.findByUserIdAndUserPw(tempLoginUserBean.getUser_id(), tempLoginUserBean.getUser_pw());

        if(findUser != null) {
            loginUserBean.setUser_idx(findUser.getUserIdx());
            loginUserBean.setUser_name(findUser.getUserName());
            loginUserBean.setUserLogin(true);
        }
    }

    public void getModifyUserInfo(UserBean modifyUserBean) {
        User tmpUser = users.findByUserIdx(loginUserBean.getUser_idx());

        modifyUserBean.setUser_idx(tmpUser.getUserIdx());
        modifyUserBean.setUser_id(tmpUser.getUserId());
        modifyUserBean.setUser_name(tmpUser.getUserName());
    }

    public void modifyUserInfo(UserBean modifyUserBean) {
        modifyUserBean.setUser_idx(loginUserBean.getUser_idx());

        User modifyUser = UserMapper.INSTANCE.toUser(modifyUserBean);
        users.save(modifyUser);
    }
}
