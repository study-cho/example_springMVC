package com.example.mapper;

import com.example.beans.ContentBean;
import org.apache.ibatis.annotations.Insert;

public interface BoardMapper {

    @Insert("INSERT INTO CONTENT_TABLE(content_idx, content_subject, content_text, " +
            "content_file, content_writer_idx, content_board_idx, content_date) " +
            "VALUES(content_seq.nextval, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
            "#{content_writer_idx}, #{content_board_idx}, sysdate)")
    void addContentInfo(ContentBean writeContentBean);
}