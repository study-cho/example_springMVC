package com.example.controller;

import com.example.beans.MessageBean;
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

    @InitBinder({"loginUserBean", "joinUserBean", "tempLoginUserBean", "modifyUserBean"})
    public void initBinder(WebDataBinder binder) {
        UserValidator validator = new UserValidator();
        binder.addValidators(validator);
    }

    @PostMapping("/join_pro")
    public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean,
                           BindingResult result, Model model) {
        if(result.hasErrors())
            return "user/join";

        userService.addUserInfo(joinUserBean);
        model.addAttribute("mes", new MessageBean("가입이 완료되었습니다","/"));
        return "common/messageRedirect";
    }

    @GetMapping("/modify")
    public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
        userService.getModifyUserInfo(modifyUserBean);
        return "user/modify";
    }

    @PostMapping("/modify_pro")
    public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean,
                             BindingResult result) {
        if(result.hasErrors()) return "user/modify";

        userService.modifyUserInfo(modifyUserBean);
        return "user/modify_success";
    }

    @GetMapping("/logout")
    public String logout() {
        loginUserBean.setUserLogin(false);
        return "user/logout";
    }

    @GetMapping("/not_login")
    public String not_login() {
        return "user/not_login";
    }
}
