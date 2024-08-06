package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.PublicationGenre;
import com.aniDB.aniDB_batch.mapper.PublicationGenreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PublicationGenreRepository {
    private final PublicationGenreMapper publicationGenreMapper;

    @Transactional
    public void bulkInsertPublicationGenre(List<PublicationGenre> publicationGenreList) {
        publicationGenreMapper.bulkInsertPublicationGenre(publicationGenreList);
    }
}