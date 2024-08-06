package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.AnimeAdaptation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnimeAdaptationMapper {
    void bulkInsertAnimeAdaptation(List<AnimeAdaptation> animeAdaptationList);
}
