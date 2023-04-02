package com.example.service;

import com.example.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public boolean checkUserIdExist(String user_id) {
        String name = userDao.checkUserIdExist(user_id);

        if(name == null)
            return true;
        else
            return false;
    }

}
