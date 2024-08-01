package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.Genre;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    void batchinsertGenres(List<Genre> genreList);
}
