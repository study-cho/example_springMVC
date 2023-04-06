package com.example.repository;

import com.example.beans.UserBean;
import com.example.entity.User;
import com.example.mapstruct.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired UserRepository users;

    @Test
    @DisplayName("저장")
    void save() {
        //given
        User user = createUser("spring", "hibernate", "1234");
        User savedUser = users.save(user);

        //when
        User findUser = users.findById(savedUser.getUserIdx()).get();

        //then
        assertThat(findUser.getUserId()).isEqualTo(savedUser.getUserId());
    }

    @Test
    @DisplayName("PK 조회")
    void findByUserIdx() {
        //given
        User user = createUser("spring", "hibernate", "1234");
        User savedUser = users.save(user);

        //when
        User findUser = users.findByUserIdx(savedUser.getUserIdx());

        //then
        assertThat(findUser.getUserId()).isEqualTo("spring");
    }

    @Test
    @DisplayName("아이디로 검색")
    void findByUserId() {
        //given
        User user = createUser("spring", "hibernate", "1234");
        User savedUser = users.save(user);

        //when
        User findUser = users.findByUserId("spring");

        //then
        assertThat(findUser).isNotNull();
    }

    @Test
    @DisplayName("아이디와 비밀번호로 조회")
    void findByUserIdAndUserPw() {
        //given
        User user = createUser("spring", "hibernate", "1234");
        User savedUser = users.save(user);

        //when
        User findUSer = users.findByUserIdAndUserPw("spring", "1235");
        User findUSer2 = users.findByUserIdAndUserPw("spring", "1234");

        // then
        assertThat(findUSer).isNull();
        assertThat(findUSer2).isNotNull();
        assertThat(findUSer2.getUserName()).isEqualTo("hibernate");

    }

    @Test
    @DisplayName("비밀번호 수정")
    void update() {
        //given
        User user = createUser("spring", "hibernate", "1234");
        User savedUser = users.save(user);

        savedUser.setUserPw("3333");
        users.save(savedUser);
        User findUser = users.findByUserIdAndUserPw("spring", "3333");

        assertThat(findUser).isNotNull();
        assertThat(findUser.getUserName()).isEqualTo("hibernate");
        assertThat(findUser.getUserPw()).isEqualTo("3333");
    }


    private User createUser(String id, String name, String pw) {
        UserBean bean = UserBean.builder()
                .user_id(id)
                .user_name(name)
                .user_pw(pw)
                .user_pw2(pw)
                .build();

        return UserMapper.INSTANCE.toUser(bean);
    }

}
