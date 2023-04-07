package com.example.service;

import com.example.beans.BoardInfoBean;
import com.example.dao.TopMenuDao;
import com.example.entity.BoardInfo;
import com.example.mapstruct.BoardInfoMapper;
import com.example.repository.BoardInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopMenuService {

    private final BoardInfoRepository repo;

    public List<BoardInfoBean> getTopMenuList() {
        List<BoardInfoBean> result = new ArrayList<>();

        List<BoardInfo> all = repo.findAll();
        for(int i=0; i<all.size(); i++) {
            result.add(BoardInfoMapper.INSTANCE.toDTO(all.get(i)));
        }

        return result;
    }
}
