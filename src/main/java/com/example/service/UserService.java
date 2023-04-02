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

    public void getModifyUserInfo(UserBean modifyUserBean) {
        UserBean tempUserBean = userDao.getModifyUserInfo(loginUserBean.getUser_idx());

        modifyUserBean.setUser_idx(tempUserBean.getUser_idx());
        modifyUserBean.setUser_id(tempUserBean.getUser_id());
        modifyUserBean.setUser_name(tempUserBean.getUser_name());
    }

    public void modifyUserInfo(UserBean modifyUserBean) {
        modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
        userDao.modifyUserInfo(modifyUserBean);
    }
}
