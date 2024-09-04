package com.aniDB.aniDB_batch.processor;

import com.aniDB.aniDB_batch.entity.*;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import com.aniDB.aniDB_batch.service.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class PublicationProcessor {
    private final PublicationService publicationService;
    private final ReadCSV<Publication> publicationReadCSV;

    @Value("${profile.dev.csv-location}")
    String csvPath;

    public void iterateOverFile() {
        for (int i = 0; i <= 79; i++) {
            String fileName = "manga_info_batch_" + String.valueOf(i);
            bulkInsertPublication(fileName);
        }
    }

    public void insertNovel() {
        String fileName = "novel_info";
        bulkInsertPublication(fileName);
    }

    void bulkInsertPublication(String fileName) {
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
                csvPath + fileName + ".csv"
        );
        publicationList.clear();
        alternativeTitleList.clear();
        relatedSeriesList.clear();
        publicationPublisherList.clear();
        publicationGenreList.clear();
        animeAdaptationList.clear();
    }



}
