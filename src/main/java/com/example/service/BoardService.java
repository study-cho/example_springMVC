package com.example.service;

import com.example.beans.ContentBean;
import com.example.beans.PageBean;
import com.example.beans.UserBean;
import com.example.dao.BoardDao;
import com.example.entity.BoardInfo;
import com.example.entity.Content;
import com.example.entity.User;
import com.example.mapstruct.ContentMapper;
import com.example.repository.BoardInfoRepository;
import com.example.repository.ContentRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:/option.properties")
public class BoardService {

//    private final BoardDao boardDao;
    private final ContentRepository contentRepo;
    private final BoardInfoRepository boardInfoRepo;
    private final UserRepository userRepo;

    @Value("${spring.servlet.multipart.location}")
    private String path_upload;

    @Value("${page.listCnt}")
    private int page_listCnt;

    @Value("${page.paginationCnt}")
    private int page_paginationCnt;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;


    private String saveUploadFile(MultipartFile upload_file) {
        String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();

        try {
            upload_file.transferTo(new File(path_upload + "/" + file_name));
        }catch (Exception e) {
            e.printStackTrace();
        }

        return file_name;
    }

    public void addContentInfo(ContentBean writeContentBean) {
        MultipartFile upload_file = writeContentBean.getUpload_file();;

        if(upload_file.getSize() > 0) {
            String file_name = saveUploadFile(upload_file);
            writeContentBean.setContent_file(file_name);
        }

        writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        writeContentBean.setContent_date(date);

        Content content = ContentMapper.INSTANCE.toEntity(writeContentBean);
        contentRepo.save(content);
    }

    public String getBoardInfoName(int board_info_idx) {
        BoardInfo boardInfo = boardInfoRepo.findById(board_info_idx).get();
        return boardInfo.getBoardInfoName();
    }

    public List<ContentBean> getContentList(int board_info_idx, int page) {

        List<Content> result = contentRepo.findByContentBoardIdxOrderByContentIdxDesc(board_info_idx, PageRequest.of(page-1, 10));

        List<ContentBean> contentBeans = new ArrayList<>();

        for (Content content : result) {
            ContentBean bean = ContentMapper.INSTANCE.toDTO(content);
            bean.setContent_writer_name(userRepo.findUserName(bean.getContent_writer_idx()));
            contentBeans.add(bean);
        }

        return contentBeans;
    }

    public ContentBean getContentInfo(int content_idx) {

        Content content = contentRepo.findById(content_idx).orElseThrow();

        ContentBean contentBean = ContentMapper.INSTANCE.toDTO(content);
        contentBean.setContent_writer_name(userRepo.findUserName(content.getContentWriterIdx()));

        return contentBean;
    }

    public void modifyContentInfo(ContentBean modifyContentBean) {
        MultipartFile upload_file = modifyContentBean.getUpload_file();

        if(upload_file.getSize() > 0) {
            String file_name = saveUploadFile(upload_file);
            modifyContentBean.setContent_file(file_name);
        }

        Content content = ContentMapper.INSTANCE.toEntity(modifyContentBean);
        contentRepo.save(content);
    }

    public void deleteContentInfo(int content_idx) {
        contentRepo.deleteById(content_idx);
    }

    public PageBean getContentCnt(int content_board_idx, int currentPage) {
        Integer contentCnt = contentRepo.countByContentBoardIdx(content_board_idx);
        return new PageBean(contentCnt, currentPage, page_listCnt, page_paginationCnt);
    }

}
