package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.repository.GenreRepository;
import com.aniDB.aniDB_batch.service.GenreService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class ReadCSVGenreImplTest {

    @Value("${profile.dev.csv-location}")
    String csvPath;

    //Repository
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    GenreService genreService;

    @Test
    void readGenreCSV() {
        ///given
        String genreCSVpath = csvPath + "genre.csv";
        //        String path = "Z:\\cms\\spring-projects\\aniDB\\aniDB_database\\aniDB_database\\resources\\csv\\genre.csv";
        List<Genre> genreList = new ArrayList<>();
        //when
        genreService.bulkinsertGenre(genreList, genreCSVpath);
        //then
        Assertions.assertThat(genreList.size()).isEqualTo(36);
        for (Genre genre: genreList){
            System.out.println(genre);
        }
    }

    @Test
    void insertUniqueGenreCSV() {
        String genreCSVpath = csvPath + "genre.csv";
        ///given
        //        String path = "Z:\\cms\\spring-projects\\aniDB\\aniDB_database\\aniDB_database\\resources\\csv\\genre.csv";
        List<Genre> genreList = new ArrayList<>();
        //when
        //then
        Assertions.assertThatThrownBy(() -> {
            genreService.bulkinsertGenre(genreList, genreCSVpath);
        }).isInstanceOf(org.springframework.dao.DuplicateKeyException.class);

    }


}