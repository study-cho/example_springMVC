package com.example.controller;

import com.example.beans.BoardInfoBean;
import com.example.beans.ContentBean;
import com.example.service.MainService;
import com.example.service.TopMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final TopMenuService topMenuService;

    @GetMapping("/main")
    public String main(Model model) {

        List<List<ContentBean>> list = new ArrayList<>();

        for(int i=1; i<=4; i++) {
            list.add(mainService.getMainList(i));
        }

        model.addAttribute("list", list);

        List<BoardInfoBean> board_list = topMenuService.getTopMenuList();
        model.addAttribute("board_list", board_list);

        return "main";
    }
}
