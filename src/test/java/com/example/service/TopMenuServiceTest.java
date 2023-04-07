package com.example.service;

import com.example.beans.BoardInfoBean;
import com.example.entity.BoardInfo;
import com.example.repository.BoardInfoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TopMenuServiceTest {

    @InjectMocks
    private TopMenuService topMenuService;

    @Mock
    private BoardInfoRepository boardInfoRepository;

    @Test
    void getTopMenuList() {
        //given
        List<BoardInfo> list = new ArrayList<>();
        list.add(new BoardInfo(1, "자유게시판"));
        list.add(new BoardInfo(2, "유머게시판"));
        list.add(new BoardInfo(3, "정치게시판"));
        list.add(new BoardInfo(4, "스포츠게시판"));

        //mocking
        given(boardInfoRepository.saveAll(any(List.class))).willReturn(list);
        given(boardInfoRepository.findAll()).willReturn(list);

        //when
        boardInfoRepository.saveAll(list);
        List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();

        //then
        assertThat(topMenuList.size()).isEqualTo(4);
        assertThat(topMenuList).extracting("board_info_idx")
                .containsOnly(1, 2, 3, 4);
        assertThat(topMenuList.get(0).getBoard_info_name()).isEqualTo("자유게시판");


    }

}