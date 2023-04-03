package com.example.controller;

import com.example.beans.ContentBean;
import com.example.beans.UserBean;
import com.example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @GetMapping("/main")
    public String main(@RequestParam("board_info_idx") int board_info_idx,
                       Model model) {
        model.addAttribute("board_info_idx", board_info_idx);

        String boardInfoName = boardService.getBoardInfoName(board_info_idx);
        model.addAttribute("boardInfoName", boardInfoName);

        List<ContentBean> contentList = boardService.getContentList(board_info_idx);
        model.addAttribute("contentList", contentList);

        return "board/main";
    }

    @GetMapping("/read")
    public String read(@RequestParam("board_info_idx") int board_info_idx,
                       @RequestParam("content_idx") int content_idx,
                       Model model) {

        model.addAttribute("board_info_idx", board_info_idx); // 목록보기
        model.addAttribute("content_idx", content_idx);

        ContentBean readContentBean = boardService.getContentInfo(content_idx);
        model.addAttribute("readContentBean", readContentBean);

        model.addAttribute("loginUserBean", loginUserBean);

        return "board/read";
    }

    @GetMapping("/write")
    public String write(@RequestParam("board_info_idx") int board_info_idx,
                        @ModelAttribute("writeContentBean") ContentBean writeContentBean) {

        writeContentBean.setContent_board_idx(board_info_idx);
        return "board/write";
    }

    @PostMapping("/write_pro")
    public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,
                            BindingResult result) {
        if(result.hasErrors())
            return "board/write";

        boardService.addContentInfo(writeContentBean);
        return "board/write_success";
    }

    @GetMapping("/modify")
    public String modify() {
        return "board/modify";
    }

    @GetMapping("/delete")
    public String delete()   {
        return "board/delete";
    }

    @GetMapping("/not_writer")
    public String not_writer() {
        return "board/not_writer";
    }
}
