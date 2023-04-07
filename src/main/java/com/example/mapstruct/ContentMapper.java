package com.example.mapstruct;

import com.example.beans.ContentBean;
import com.example.entity.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentMapper {

    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    @Mappings({
            @Mapping(source = "contentIdx", target = "content_idx"),
            @Mapping(source = "contentSubject", target = "content_subject"),
            @Mapping(source = "contentText", target = "content_text"),
            @Mapping(source = "contentFile", target = "content_file"),
            @Mapping(source = "contentWriterIdx", target = "content_writer_idx"),
            @Mapping(source = "contentBoardIdx", target = "content_board_idx"),
            @Mapping(source = "contentDate", target = "content_date")
    })
    ContentBean toDTO(Content content);

    @Mappings({
            @Mapping(source = "content_idx", target = "contentIdx"),
            @Mapping(source = "content_subject", target = "contentSubject"),
            @Mapping(source = "content_text", target = "contentText"),
            @Mapping(source = "content_file", target = "contentFile"),
            @Mapping(source = "content_writer_idx", target = "contentWriterIdx"),
            @Mapping(source = "content_board_idx", target = "contentBoardIdx"),
            @Mapping(source = "content_date", target = "contentDate")
    })
    Content toEntity(ContentBean contentBean);
}