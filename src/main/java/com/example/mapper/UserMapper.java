package com.example.mapper;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT user_name " +
            "FROM USER_TABLE " +
            "WHERE user_id = #{user_id}")
    String checkUserIdExist(String user_id);
}
