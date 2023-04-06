package com.example.mapstruct;

import com.example.beans.UserBean;
import com.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "userId", target = "user_id"),
            @Mapping(source = "userName", target = "user_name"),
            @Mapping(source = "userPw", target = "user_pw")
    })
    UserBean toUserBean(User user);

    @Mappings({
            @Mapping(source = "user_id", target = "userId"),
            @Mapping(source = "user_name", target = "userName"),
            @Mapping(source = "user_pw", target = "userPw")
    })
    User toUser(UserBean userBean);

}