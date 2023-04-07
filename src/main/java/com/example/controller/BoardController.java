package com.example.controller;

import com.example.beans.ContentBean;
import com.example.beans.PageBean;
import com.example.beans.UserBean;
import com.example.common.MessageBean;
import com.example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.example.common.AlertMessages.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @GetMapping("/main")
    public String main(@RequestParam("board_info_idx") int board_info_idx,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       Model model) {
        model.addAttribute("board_info_idx", board_info_idx);

        String boardInfoName = boardService.getBoardInfoName(board_info_idx);
        model.addAttribute("boardInfoName", boardInfoName);

        List<ContentBean> contentList = boardService.getContentList(board_info_idx, page);
        model.addAttribute("contentList", contentList);

        PageBean pageBean = boardService.getContentCnt(board_info_idx, page);
        model.addAttribute("pageBean", pageBean);

        model.addAttribute("page", page);

        return "board/main";
    }

    @GetMapping("/read")
    public String read(@RequestParam("board_info_idx") Integer board_info_idx,
                       @RequestParam("content_idx") Integer content_idx,
                       @RequestParam("page") Integer page,
                       Model model) {

        model.addAttribute("board_info_idx", board_info_idx); // 목록보기
        model.addAttribute("content_idx", content_idx);

        ContentBean readContentBean = boardService.getContentInfo(content_idx);
        model.addAttribute("readContentBean", readContentBean);

        model.addAttribute("loginUserBean", loginUserBean);
        model.addAttribute("page", page);

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
                            BindingResult result, Model model) {
        if(result.hasErrors())
            return "board/write";

        boardService.addContentInfo(writeContentBean);
        model.addAttribute("data", new MessageBean(WRITE_SUCCESS.getMessage(),
                "/board/read?board_info_idx="+writeContentBean.getContent_board_idx()+"&content_idx="+writeContentBean.getContent_idx()+"&page=1"));

        return "common/messageRedirect";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("board_info_idx") int board_info_idx,
                         @RequestParam("content_idx") int content_idx,
                         @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
                         @RequestParam("page") int page,
                         Model model) {

        model.addAttribute("board_info_idx", board_info_idx);
        model.addAttribute("content_idx", content_idx);

        ContentBean temp = boardService.getContentInfo(content_idx);

        modifyContentBean.setContent_board_idx(board_info_idx);
        modifyContentBean.setContent_idx(content_idx);
        modifyContentBean.setContent_writer_idx(temp.getContent_writer_idx());
        modifyContentBean.setContent_writer_name(temp.getContent_writer_name());
        modifyContentBean.setContent_date(temp.getContent_date());
        modifyContentBean.setContent_text(temp.getContent_text());
        modifyContentBean.setContent_subject(temp.getContent_subject());
        modifyContentBean.setContent_file(temp.getContent_file());

        model.addAttribute("page", page);

        return "board/modify";
    }

    @PostMapping("/modify_pro")
    public String modify_pro(@Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
                             BindingResult result,
                             @RequestParam("page") int page,
                             Model model) {

        if(result.hasErrors()) {
            return "board/modify";
        }

        boardService.modifyContentInfo(modifyContentBean);

        model.addAttribute("data", new MessageBean(WRITE_SUCCESS.getMessage(),
                "/board/read?board_info_idx="+modifyContentBean.getContent_board_idx()+"&content_idx="+modifyContentBean.getContent_idx()+"&page="+ page));

        return "common/messageRedirect";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("board_info_idx") int board_info_idx,
                         @RequestParam("content_idx") int content_idx,
                         @RequestParam("page") int page,
                         Model model)   {
        boardService.deleteContentInfo(content_idx);

        model.addAttribute("data", new MessageBean(DELETE.getMessage(), "/board/main?board_info_idx="+board_info_idx+"&page="+page));
        return "common/messageRedirect";
    }

    @GetMapping("/not_writer")
    public String not_writer(Model model) {
        model.addAttribute("data", new MessageBean(NOT_WRITER.getMessage(), "/"));
        return "common/messageRedirect";
    }
}
