package com.aniDB.aniDB_batch.processor;

import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import com.aniDB.aniDB_batch.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class GenreProcessor {
    private final GenreService genreService;
    private final ReadCSV<Genre> genreReadCSV;


    @Value("${profile.dev.csv-location}")
    String csvPath;


    public void insertAllGenre() {
        List<Genre> genreList = new ArrayList<>();
        genreReadCSV.readCSV(genreList, csvPath + "genre.csv");
        genreService.bulkinsertGenre(genreList, csvPath + "genre.csv");
    }

}
