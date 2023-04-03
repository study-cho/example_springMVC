package com.example.service;

import com.example.beans.ContentBean;
import com.example.dao.BoardDao;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final BoardDao boardDao;

    public List<ContentBean> getMainList(int board_info_idx) {
        RowBounds rowBounds = new RowBounds(0, 5);
        return boardDao.getContentList(board_info_idx, rowBounds);
    }


}
