package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publisher;
import com.aniDB.aniDB_batch.mapper.PublisherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
