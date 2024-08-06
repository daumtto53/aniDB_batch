package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.AlternativeTitle;
import com.aniDB.aniDB_batch.entity.Publication;
import com.aniDB.aniDB_batch.mapper.AlternativeTitleMapper;
import com.aniDB.aniDB_batch.mapper.PublicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PublicationRepository {
    private final PublicationMapper publicationMapper;
    private final AlternativeTitleMapper alternativeTitleMapper;

    @Transactional
    public void bulkInsertPublication(List<Publication> publicationList) {
        publicationMapper.bulkInsertPublication(publicationList);
    }

}
