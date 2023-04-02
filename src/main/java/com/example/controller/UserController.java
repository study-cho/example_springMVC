package com.example.controller;

import com.example.beans.UserBean;
import com.example.service.UserService;
import com.example.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @GetMapping("/login")
    public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
                        @RequestParam(value = "fail", defaultValue = "false") boolean fail,
                        Model model) {

        model.addAttribute("fail", fail);
        return "user/login";
    }

    @PostMapping("/login_pro")
    public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
                            BindingResult result) {
        if(result.hasErrors()) return "user/login";

        userService.getLoginUserInfo(tempLoginUserBean);

        if(loginUserBean.isUserLogin())
            return "user/login_success";
        else
            return "user/login_fail";
//            return "redirect:/user/login?fail=true";
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

        userService.addUserInfo(joinUserBean);
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
