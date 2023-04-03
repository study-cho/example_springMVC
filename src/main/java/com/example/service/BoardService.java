package com.example.service;

import com.example.beans.ContentBean;
import com.example.beans.UserBean;
import com.example.dao.BoardDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Service
@RequiredArgsConstructor
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

    private final BoardDao boardDao;

    @Value("${path.upload}")
    private String path_upload;

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

        boardDao.addContentInfo(writeContentBean);
    }
}
