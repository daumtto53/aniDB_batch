package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.dto.PublisherRelationDTO;
import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publisher;
import com.aniDB.aniDB_batch.mapper.PublisherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PublisherRepository {
    private final PublisherMapper publisherMapper;

    @Transactional
    public List<Publisher> bulkInsertPublisher(List<Publisher> publisherList) {
        publisherMapper.bulkInsertPublisher(publisherList);
        return publisherList;
    }

    @Transactional
    public List<AlternativePublisherName> bulkInsertAlternativePublisherName(
            List<AlternativePublisherName> alternativePublisherNameList ) {
        publisherMapper.bulkInsertAlternativePublisherName(alternativePublisherNameList);
        return alternativePublisherNameList;
    }

    @Transactional
    public Map<String, Integer> selectPublisherMapOfNameAndId() {
        List<Map<String, Object>> publisherNameIdListMap = publisherMapper.selectPublisherMapOfNameAndId();
        HashMap<String, Integer> collect = publisherNameIdListMap.stream()
                .collect(
                        Collectors.toMap(
                                map -> (String) (map.get("name")),
                                map -> (Integer) map.get("id"),
                                (existing, replacement) -> existing,
                                HashMap::new
                        )
                );
        return collect;
    }

    @Transactional
    public void updatePublisherForLabelPublisherRelation(List<PublisherRelationDTO> publisherRelationDTOList) {
        publisherMapper.updatePublisherForLabelPublisherRelation(publisherRelationDTOList);
    }

}
