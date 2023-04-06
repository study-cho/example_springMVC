package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    @Column(name = "user_idx")
    private Integer userIdx;

    @Column(name="user_name")
    private String userName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;
}
