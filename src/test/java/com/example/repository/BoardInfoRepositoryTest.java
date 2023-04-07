package com.example.repository;

import com.example.entity.BoardInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BoardInfoRepositoryTest {

    @Autowired BoardInfoRepository boardInfoRepository;

    @Test
    @DisplayName("목록 가져오기")
    void findAll() {
        //given
        saveBoardInfo();

        //when
        List<BoardInfo> boardInfoList = boardInfoRepository.findAll();

        //then
        assertThat(boardInfoList.size()).isEqualTo(4);
        assertThat(boardInfoList).extracting("boardInfoName")
                .containsOnly("자유게시판", "유머게시판", "정치게시판", "스포츠게시판");
    }

    @Test
    @DisplayName("게시판 이름 가져오기")
    void getBoardInfoName() {
        //given
        saveBoardInfo();

        //when
        Optional<BoardInfo> findBoardInfo = boardInfoRepository.findById(2);

        //then
        assertThat(findBoardInfo.get().getBoardInfoName()).isEqualTo("유머게시판");

    }

    private void saveBoardInfo() {
        List<BoardInfo> list = new ArrayList<>();
        list.add(new BoardInfo(1, "자유게시판"));
        list.add(new BoardInfo(2, "유머게시판"));
        list.add(new BoardInfo(3, "정치게시판"));
        list.add(new BoardInfo(4, "스포츠게시판"));
        boardInfoRepository.saveAll(list);
    }
}