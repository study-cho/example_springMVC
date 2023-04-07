package com.example.repository;

import com.example.entity.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {

    List<Content> findByContentBoardIdxOrderByContentIdxDesc(int ContentBoardIdx, Pageable pageable);

    Integer countByContentBoardIdx(int ContentBoardIdx);
}
