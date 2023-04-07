package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "board_info_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardInfo {

    @Id
    @Column(name = "board_info_idx")
    private int boardInfoIdx;

    @Column(name = "board_info_name")
    private String boardInfoName;
}
