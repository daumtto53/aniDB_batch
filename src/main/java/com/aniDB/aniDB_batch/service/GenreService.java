package com.aniDB.aniDB_batch.service;

import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import com.aniDB.aniDB_batch.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final ReadCSV<Genre> genreReadCSV;
    private final GenreRepository genreRepository;

    public void bulkinsertGenre(List<Genre> genreList, String path){
        genreReadCSV.readCSV(genreList, path);
        genreRepository.addGenres(genreList);
    }

}
