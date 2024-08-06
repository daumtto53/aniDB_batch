package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.PublicationPublisher;
import com.aniDB.aniDB_batch.mapper.PublicationPublisherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PublicationPublisherRepository {
    private final PublicationPublisherMapper publicationPublisherMapper;

    @Transactional
    public void bulkInsertPublicationPublisher(List<PublicationPublisher> publicationPublisherList) {
        publicationPublisherMapper.bulkInsertPublicationPublisher(publicationPublisherList);
    }
}