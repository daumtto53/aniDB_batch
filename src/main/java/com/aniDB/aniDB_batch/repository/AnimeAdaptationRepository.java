package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.AnimeAdaptation;
import com.aniDB.aniDB_batch.mapper.AnimeAdaptationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class AnimeAdaptationRepository {
    private final AnimeAdaptationMapper animeAdaptationMapper;

    @Transactional
    public void bulkInsertAnimeAdaptation(List<AnimeAdaptation> animeAdaptationList) {
        animeAdaptationMapper.bulkInsertAnimeAdaptation(animeAdaptationList);
    }
}