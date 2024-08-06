package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.RelatedSeries;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RelatedSeriesMapper {
    void bulkInsertRelatedSeries(List<RelatedSeries> relatedSeriesList);
}

