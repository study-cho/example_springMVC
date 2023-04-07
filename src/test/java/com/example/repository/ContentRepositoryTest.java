package com.example.repository;

import com.example.entity.Content;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ContentRepositoryTest {

    @Autowired ContentRepository contentRepo;

    @Test
    @DisplayName("저장")
    void save() {
        //given
        Content content1 = createContent("제목", "내용", null, 1, 2, "20230407");

        //when
        Content savedContent = contentRepo.save(content1);

        //then
        Optional<Content> result = contentRepo.findById(savedContent.getContentIdx());
        assertThat(result.get().getContentIdx()).isEqualTo(savedContent.getContentIdx());

    }

    @Test
    @DisplayName("게시판별 조회")
    void findByContentBoardIdx() {
        //given
        saveContentList();

        //when
        List<Content> result = contentRepo.findByContentBoardIdxOrderByContentIdxDesc(1, PageRequest.of(0, 10));

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getContentSubject()).isEqualTo("제목4");
    }


    @Test
    @DisplayName("수정")
    void update() {
        //given
        Content content1 = createContent("제목", "내용", null, 1, 2, "20230407");
        Content savedContent = contentRepo.save(content1);

        //when
        content1.setContentSubject("수정된제목");
        Content modifyContent = contentRepo.save(content1);

        //then
        Optional<Content> result = contentRepo.findById(savedContent.getContentIdx());
        assertThat(result.get().getContentSubject()).isEqualTo("수정된제목");

    }

    @Test
    @DisplayName("삭제")
    void delete() {
        //given
        saveContentList();

        //when
        contentRepo.deleteById(1); //제목1

        //then
        List<Content> result = contentRepo.findAll();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).extracting("contentSubject", String.class)
                .contains("제목2", "제목3", "제목4");
        assertThat(result).extracting("contentSubject", String.class)
                .doesNotContain("제목1");
    }

    @Test
    @DisplayName("카운트 구하기")
    void count() {
        //given
        saveContentList();

        //when
        Integer result = contentRepo.countByContentBoardIdx(1);// 2

        //then
        assertThat(result).isEqualTo(2);

    }

    private Content createContent(String subject, String text, String file, int writerIdx, int boardIdx, String date) {
        return Content.builder()
                .contentText(text)
                .contentSubject(subject)
                .contentFile(file)
                .contentWriterIdx(writerIdx)
                .contentBoardIdx(boardIdx)
                .contentDate(date)
                .build();
    }

    private void saveContentList() {
        List<Content> list = new ArrayList<>();
        list.add(createContent("제목1", "내용1", null, 1, 1, "20230407"));
        list.add(createContent("제목2", "내용2", null, 1, 2, "20230407"));
        list.add(createContent("제목3", "내용3", null, 2, 4, "20230407"));
        list.add(createContent("제목4", "내용4", null, 3, 1, "20230407"));
        contentRepo.saveAll(list);
    }

}