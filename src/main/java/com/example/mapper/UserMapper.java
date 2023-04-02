package com.example.mapper;

import com.example.beans.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT user_name " +
            "FROM USER_TABLE " +
            "WHERE user_id = #{user_id}")
    String checkUserIdExist(String user_id);

    @Insert("INSERT INTO USER_TABLE (user_idx, user_name, user_id, user_pw) " +
            "VALUES (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
    void addUserInfo(UserBean joinUserBean);

    @Select("SELECT user_idx, user_name FROM USER_TABLE " +
            "WHERE user_id=#{user_id} AND user_pw=#{user_pw}")
    UserBean getLoginUserInfo(UserBean tempLoginUserBean);

}