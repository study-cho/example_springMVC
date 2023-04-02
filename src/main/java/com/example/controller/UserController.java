package com.example.controller;

import com.example.beans.UserBean;
import com.example.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {



    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute("joinUserBean")UserBean joinUserBean) {
        return "user/join";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        UserValidator validator = new UserValidator();
        binder.addValidators(validator);
    }

    @PostMapping("/join_pro")
    public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean,
                           BindingResult result) {
        if(result.hasErrors())
            return "user/join";

        return "user/join_success";
    }

    @GetMapping("/modify")
    public String modify() {
        return "user/modify";
    }

    @GetMapping("/logout")
    public String logout() {
        return "user/logout";
    }
}
