package com.example.service;

import com.example.beans.BoardInfoBean;
import com.example.dao.TopMenuDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopMenuService {

    private final TopMenuDao topMenuDao;

    public List<BoardInfoBean> getTopMenuList() {
        return topMenuDao.getTopMenuList();
    }
}
