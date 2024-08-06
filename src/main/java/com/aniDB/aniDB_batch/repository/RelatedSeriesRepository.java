package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.RelatedSeries;
import com.aniDB.aniDB_batch.mapper.RelatedSeriesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class RelatedSeriesRepository {
    private final RelatedSeriesMapper relatedSeriesMapper;

    @Transactional
    public void bulkInsertRelatedSeries(List<RelatedSeries> relatedSeriesList) {
        relatedSeriesMapper.bulkInsertRelatedSeries(relatedSeriesList);
    }
}