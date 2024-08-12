package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.dto.PublisherRelationDTO;
import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publisher;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PublisherMapper {
    void bulkInsertPublisher(List<Publisher> publisherList);
    void bulkInsertAlternativePublisherName(List<AlternativePublisherName> alternativePublisherNameList);

    void bulkInsertPublisherWithLabel(List<Publisher> publisherList);

    List<Map<String, Object>> selectPublisherMapOfNameAndId();

    void updatePublisherForLabelPublisherRelation(List<PublisherRelationDTO> publisherRelationDTOList);

    void updatePublisherForLabelPublisherRelationTest(PublisherRelationDTO publisherRelationDTO);
}
