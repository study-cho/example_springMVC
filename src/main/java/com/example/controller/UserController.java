package com.example.controller;

import com.example.common.MessageBean;
import com.example.beans.UserBean;
import com.example.common.AlertMessages;
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

import static com.example.common.AlertMessages.*;

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
                            BindingResult result, Model model) {
        if(result.hasErrors()) return "user/login";

        userService.getLoginUserInfo(tempLoginUserBean);

        if(loginUserBean.isUserLogin())
            model.addAttribute("data", new MessageBean(LOGIN_SUCCESS.getMessage(), "/main"));
        else
            model.addAttribute("data", new MessageBean(LOGIN_FAIL.getMessage(), "/user/login?fail=true"));

        return "common/messageRedirect";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute("joinUserBean")UserBean joinUserBean) {
        return "user/join";
    }

    // MessageBean 때문에 @Valid 충돌 ==> 사용할 빈 지정
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
        model.addAttribute("data", new MessageBean(JOIN_SUCCESS.getMessage(),"/"));
        return "common/messageRedirect";
    }

    @GetMapping("/modify")
    public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
        userService.getModifyUserInfo(modifyUserBean);
        return "user/modify";
    }

    @PostMapping("/modify_pro")
    public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean,
                             BindingResult result, Model model) {
        if(result.hasErrors()) return "user/modify";

        userService.modifyUserInfo(modifyUserBean);
        model.addAttribute("data", new MessageBean(MODIFY_SUCCESS.getMessage(), "/user/modify"));
        return "common/messageRedirect";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        loginUserBean.setUserLogin(false);
        model.addAttribute("data", new MessageBean(LOGOUT.getMessage(), "/"));
        return "common/messageRedirect";
    }

    @GetMapping("/not_login")
    public String not_login(Model model) {
        model.addAttribute("data", new MessageBean(NOT_LOGIN.getMessage(), "/user/login"));
        return "common/messageRedirect";
    }
}
