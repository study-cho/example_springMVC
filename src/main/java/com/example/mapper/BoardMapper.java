package com.example.mapper;

import com.example.beans.ContentBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface BoardMapper {

    @SelectKey(statement = "SELECT content_seq.nextval FROM dual",
            keyProperty = "content_idx", before = true, resultType = int.class)

    @Insert("INSERT INTO CONTENT_TABLE(content_idx, content_subject, content_text, " +
            "content_file, content_writer_idx, content_board_idx, content_date) " +
            "VALUES(#{content_idx}, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
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
    List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds);

    @Select("SELECT ut.user_name AS content_writer_name, " +
            "to_char(ct.content_date, 'YYYY-MM-DD') AS content_date, " +
            "ct.content_subject, ct.content_text, ct.content_file, ct.content_writer_idx " +
            "FROM CONTENT_TABLE ct, USER_TABLE ut " +
            "WHERE ct.content_writer_idx = ut.user_idx " +
            "and content_idx=#{content_idx}")
    ContentBean getContentInfo(int content_idx);

    @Update("UPDATE content_table " +
            "SET content_subject = #{content_subject}, content_text = #{content_text}, " +
            "content_file = #{content_file, jdbcType=VARCHAR} " +
            "WHERE content_idx = #{content_idx}")
    void modifyContentInfo(ContentBean modifyContentBean);

    @Delete("DELETE FROM content_table WHERE content_idx = #{content_idx}")
    void deleteContentInfo(int content_idx);

    @Select("SELECT count(*) FROM content_table WHERE content_board_idx = #{content_board_idx}")
    int getContentCnt(int content_board_idx);
}