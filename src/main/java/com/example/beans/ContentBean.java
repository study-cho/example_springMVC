package com.example.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ContentBean {

    private int content_idx;

    @NotBlank
    private String content_subject;
    @NotBlank
    private String content_text;

    private MultipartFile upload_file;

    private String content_file;
    private int content_writer_idx;
    private int content_board_idx;
    private String content_date;
    private String content_writer_name;
}
