package com.aniDB.aniDB_batch.service;

import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.entity.SeriesType;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("dev")
class GenreServiceTest {

    @Autowired
    GenreService genreService;
    @Autowired
    ReadCSV<Genre> genreReadCSV;


    @Value("${profile.dev.csv-location}")
    String csvPath;


    @Test
    void insertAllSeriesType() {
        List<Genre> genreList = new ArrayList<>();
        genreReadCSV.readCSV(genreList, csvPath + "genre.csv");
        genreService.bulkinsertGenre(genreList, csvPath);
    }

    @Test
    void getSeriesTypeAsMap() {
        Map<String, Integer> genreAsMap = genreService.getAllGenres();
        System.out.println(genreAsMap);
    }
}