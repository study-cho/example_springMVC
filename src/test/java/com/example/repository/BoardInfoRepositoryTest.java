package com.example.repository;

import com.example.entity.BoardInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BoardInfoRepositoryTest {

    @Autowired BoardInfoRepository boardInfoRepository;

    @Test
    @DisplayName("목록 가져오기")
    void findAll() {
        //given
        BoardInfo info1 = new BoardInfo(1, "자유게시판");
        BoardInfo info2 = new BoardInfo(2, "유머게시판");
        BoardInfo info3 = new BoardInfo(3, "정치게시판");
        BoardInfo info4 = new BoardInfo(4, "스포츠게시판");
        boardInfoRepository.save(info1);
        boardInfoRepository.save(info2);
        boardInfoRepository.save(info3);
        boardInfoRepository.save(info4);

        //when
        List<BoardInfo> boardInfoList = boardInfoRepository.findAll();

        //then
//        boardInfoList.stream().forEach(c -> System.out.println(c.getBoardInfoName()));
        assertThat(boardInfoList.size()).isEqualTo(4);
    }
}