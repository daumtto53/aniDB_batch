package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.Genre;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GenreMapper {
    void batchinsertGenres(List<Genre> genreList);
    List<Map<String, Object>> selectAllGenres();
}
