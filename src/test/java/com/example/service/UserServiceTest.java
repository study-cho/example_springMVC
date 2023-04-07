package com.example.service;

import com.example.beans.UserBean;
import com.example.entity.User;
import com.example.mapstruct.UserMapper;
import com.example.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void checkUserIdExist() {
        // given
        UserBean userBean = createUserBean("spring", "hibernate", "1234");
        Integer fakeUserIdx = 1;
        User user = UserMapper.INSTANCE.toUser(userBean);
        ReflectionTestUtils.setField(user, "userIdx", fakeUserIdx);

        //mocking
        given(userRepository.save(any())).willReturn(user);
        given(userRepository.findByUserId("spring")).willReturn(user);

        //when
        userRepository.save(user);
        boolean checkId = userService.checkUserIdExist("spring");

        //then
        assertThat(checkId).isFalse();
    }

    @Test
    void addUserInfo() {
        // given
        UserBean userBean = createUserBean("spring", "hibernate", "1234");
        Integer fakeUserIdx = 1;
        User user = UserMapper.INSTANCE.toUser(userBean);
        ReflectionTestUtils.setField(user, "userIdx", fakeUserIdx);

        //mocking
        given(userRepository.save(any())).willReturn(user);
        given(userRepository.findByUserIdx(fakeUserIdx)).willReturn(user);

        //when
        UserBean userBean1 = createUserBean("spring", "hibernate", "1234");
        userService.addUserInfo(userBean1);

        //then
        User findUser = userRepository.findByUserIdx(1);
        Assertions.assertThat(findUser.getUserId()).isEqualTo(userBean1.getUser_id());

    }


    private UserBean createUserBean(String user_id, String user_name, String user_pw) {
        return UserBean.builder()
                .user_id(user_id)
                .user_name(user_name)
                .user_pw(user_pw)
                .build();
    }
}