package com.example.dao;

import com.example.beans.ContentBean;
import com.example.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final BoardMapper boardMapper;

    public void addContentInfo(ContentBean writeContentBean) {
        boardMapper.addContentInfo(writeContentBean);
    }

    public String getBoardInfoName(int board_info_idx) {
        return boardMapper.getBoardInfoName(board_info_idx);
    }

    public List<ContentBean> getContentList(int board_info_idx) {
        return boardMapper.getContentList(board_info_idx);
    }
}
