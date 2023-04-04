package com.example.mapper;

import com.example.beans.BoardInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopMenuMapper {

    @Select("SELECT board_info_idx, board_info_name " +
            "FROM BOARD_INFO_TABLE " +
            "ORDER BY board_info_idx")
    List<BoardInfoBean> getTopMenuInfo();
}
