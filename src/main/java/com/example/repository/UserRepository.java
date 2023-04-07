package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(String user_id);
    User findByUserIdx(int user_idx);
    User findByUserIdAndUserPw(String user_id, String user_pw);

    @Query("select u.userName from User u where u.userIdx=:userIdx")
    String findUserName(int userIdx) ;
}
