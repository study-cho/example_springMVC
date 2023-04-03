package com.example.dao;

import com.example.beans.ContentBean;
import com.example.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final BoardMapper boardMapper;

    public void addContentInfo(ContentBean writeContentBean) {
        boardMapper.addContentInfo(writeContentBean);
    }
}
