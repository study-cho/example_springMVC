package com.example.repository;

import com.example.entity.BoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardInfoRepository extends JpaRepository<BoardInfo, Integer> {
}
