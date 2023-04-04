package com.example.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageBean {

    private String message;             //전달할 메시지
    private String redirectUri;         //리다이렉트URI

}
