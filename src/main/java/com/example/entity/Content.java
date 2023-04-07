package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "content_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_seq_gen")
    @SequenceGenerator(name = "content_seq_gen", sequenceName = "content_seq", allocationSize = 1)
    @Column(name = "content_idx")
    private int contentIdx;

    @Column(name="content_subject")
    private String contentSubject;

    @Column(name = "content_text")
    private String contentText;

    @Column(name = "content_file")
    private String contentFile;

    @Column(name = "content_writer_idx")
    private int contentWriterIdx;

    @Column(name = "content_board_idx")
    private int contentBoardIdx;

    @Column(name = "content_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contentDate;
}
