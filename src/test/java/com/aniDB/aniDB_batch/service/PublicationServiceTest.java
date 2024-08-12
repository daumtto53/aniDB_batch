package com.aniDB.aniDB_batch.service;

import com.aniDB.aniDB_batch.entity.*;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class PublicationServiceTest {

    @Autowired
    PublicationService publicationService;
    @Autowired
    ReadCSV<Publication> publicationReadCSV;


    @Value("${profile.dev.csv-location}")
    String csvPath;



    @Test
    void bulkInsertPublication() {
        List<Publication> publicationList = new ArrayList<>();
        List<AlternativeTitle> alternativeTitleList = new ArrayList<>();
        List<RelatedSeries> relatedSeriesList = new ArrayList<>();
        List<PublicationPublisher> publicationPublisherList = new ArrayList<>();
        List<PublicationGenre> publicationGenreList = new ArrayList<>();
        List<AnimeAdaptation> animeAdaptationList = new ArrayList<>();

        publicationService.bulkInsertPublication(
                publicationList,
                alternativeTitleList,
                relatedSeriesList,
                publicationPublisherList,
                publicationGenreList,
                animeAdaptationList,
                csvPath + "novel_info.csv"
        );
    }
}