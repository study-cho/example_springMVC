package com.example.mapper;

import com.example.beans.ContentBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardMapper {

    @Insert("INSERT INTO CONTENT_TABLE(content_idx, content_subject, content_text, " +
            "content_file, content_writer_idx, content_board_idx, content_date) " +
            "VALUES(content_seq.nextval, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
            "#{content_writer_idx}, #{content_board_idx}, sysdate)")
    void addContentInfo(ContentBean writeContentBean);

    @Select("SELECT board_info_name " +
            "FROm board_info_table " +
            "WHERE board_info_idx = #{board_info_idx}")
    String getBoardInfoName(int board_info_idx);

    @Select("SELECT ct.content_idx, ct.content_subject, ut.user_name AS content_writer_name, " +
            "to_char(ct.content_date, 'YYYY-MM-DD') AS content_date " +
            "FROM CONTENT_TABLE ct, USER_TABLE ut " +
            "WHERE ct.content_writer_idx = ut.user_idx " +
            "AND ct.content_board_idx = #{board_info_idx}" +
            "ORDER BY ct.content_idx DESC")
    List<ContentBean> getContentList(int board_info_idx);
}