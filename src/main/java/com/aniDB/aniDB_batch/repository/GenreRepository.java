package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class GenreRepository {

    private final GenreMapper genreMapper;

    @Transactional
    public List<Genre> addGenres(List<Genre> genreList) {
        genreMapper.batchinsertGenres(genreList);
        return genreList;
    }

}
