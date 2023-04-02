package com.example.controller;

import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final UserService userService;

    @GetMapping("/user/checkUserIdExist/{user_id}")
    public String checkUserIdExist(@PathVariable String user_id) {
        boolean chk = userService.checkUserIdExist(user_id);
        return chk + "";
    }

}
