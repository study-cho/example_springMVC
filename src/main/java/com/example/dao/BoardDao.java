package com.example.dao;

import com.example.beans.ContentBean;
import com.example.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
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

    public List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds) {
        return boardMapper.getContentList(board_info_idx, rowBounds);
    }

    public ContentBean getContentInfo(int content_idx) {
        return boardMapper.getContentInfo(content_idx);
    }

    public void modifyContentInfo(ContentBean modifyContentBean) {
        boardMapper.modifyContentInfo(modifyContentBean);
    }

    public void deleteContentInfo(int content_idx) {
        boardMapper.deleteContentInfo(content_idx);
    }

    public int getContentCnt(int content_board_idx) {
        return boardMapper.getContentCnt(content_board_idx);
    }
}
