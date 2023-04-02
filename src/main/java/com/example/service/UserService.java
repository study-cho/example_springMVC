package com.example.service;

import com.example.beans.UserBean;
import com.example.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    public boolean checkUserIdExist(String user_id) {
        String name = userDao.checkUserIdExist(user_id);

        if(name == null)
            return true;
        else
            return false;
    }

    public void addUserInfo(UserBean joinUserBean) {
        userDao.addUserInfo(joinUserBean);
    }

    public void getLoginUserInfo(UserBean tempLoginUserBean) {
        UserBean tempUserBean = userDao.getLoginUserInfo(tempLoginUserBean);

        if(tempUserBean != null) {
            loginUserBean.setUser_idx(tempUserBean.getUser_idx());
            loginUserBean.setUser_name(tempUserBean.getUser_name());
            loginUserBean.setUserLogin(true);
        }
    }

}
