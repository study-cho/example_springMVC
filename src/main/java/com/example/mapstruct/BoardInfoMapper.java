package com.example.mapstruct;

import com.example.beans.BoardInfoBean;
import com.example.entity.BoardInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardInfoMapper {

    BoardInfoMapper INSTANCE = Mappers.getMapper(BoardInfoMapper.class);

    @Mappings({
            @Mapping(source = "boardInfoIdx", target = "board_info_idx"),
            @Mapping(source = "boardInfoName", target = "board_info_name")
    })
    BoardInfoBean toDTO(BoardInfo boardInfo);

    @Mappings({
            @Mapping(source = "board_info_idx", target = "boardInfoIdx"),
            @Mapping(source = "board_info_name", target = "boardInfoName")
    })
    BoardInfo toEntity(BoardInfoBean boardInfoBean);
}