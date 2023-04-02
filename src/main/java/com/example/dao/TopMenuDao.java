package com.example.dao;

import com.example.beans.BoardInfoBean;
import com.example.mapper.TopMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TopMenuDao {

    private final TopMenuMapper topMenuMapper;

    public List<BoardInfoBean> getTopMenuList() {
        return topMenuMapper.getTopMenuInfo();
    }

}
