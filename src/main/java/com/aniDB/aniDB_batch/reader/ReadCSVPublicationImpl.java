package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.dto.GsonAnimeAdaptationBinder;
import com.aniDB.aniDB_batch.dto.GsonPublisherBinder;
import com.aniDB.aniDB_batch.dto.PublisherRelation;
import com.aniDB.aniDB_batch.entity.*;
import com.aniDB.aniDB_batch.preprocessor.PublicationPreprocessor;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
    1. title
    2. desc
    3. type
    4. year
    5. array of related-series : {title, type, relation} ...
    6. alternative_title : {title, language}
    7. status in original country {volume, status}
    8. image_url
    10. authors []
    9. artists []
    11. publisher {publisher, label}
        publisher , label 넣으면서 관계도 다시 정립할 것.
    12. publisher, label
    13. genres []
    14. anime_adaptation  {volume start,anime_start, volume_end, anime_end} arrays.
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class ReadCSVPublicationImpl implements ReadCSV<Publication> {

    private final PublicationPreprocessor publicationPreprocessor;
    private final PublisherRelation publisherRelation;

    @Override
    public List<Publication> readCSV(List<Publication> retrieveList, String csvPath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))){
            String line[];
            while ((line = reader.readNext()) != null) {
                String title = line[0].strip();

//                log.info("title={}", title);

                String description = line[1].strip();
                String type = line[2].strip().equals("Novel") ? "Light Novel" : line[2].strip();
                int typeId = publicationPreprocessor.mapTypeToInt(type);
                LocalDate year;
                try {
                    year = publicationPreprocessor.yearStringToLocalDateTime(line[3]).toLocalDate();
                } catch (NullPointerException e) {
                    year = null;
                }
                List<Map<String, String>> relatedSeriesList = publicationPreprocessor.getRealatedSeriesList(line[4]);
                List<Map<String, String>> alternativeTitleList = publicationPreprocessor.getAlternativeTitleList(line[5]);
                VolumeStatus volumeStatus = publicationPreprocessor.getVolumeStatus(line[6]);
                String url = publicationPreprocessor.getImageURL(line[7]);
                List<String> authorList = publicationPreprocessor.getAuthorList(line[9]);
                List<String> artistList = publicationPreprocessor.getArtistList(line[8]);
                List<Map<String ,String >> publisherList = publicationPreprocessor.getPublisherList(line[10]);
                List<Map<String ,String >> labelList = publicationPreprocessor.getPublisherList(line[11]);
                List<String> genreList = publicationPreprocessor.getGenreList(line[12]);
                List<Genre> genres = publicationPreprocessor.getGenreIdList(genreList);
                List<AnimeAdaptation> animeAdaptationList = publicationPreprocessor.getAnimeAdaptationList(line[13]);


                List<RelatedSeries> relatedSeries = publicationPreprocessor.convertMapToRelatedSeries(relatedSeriesList);
                List<AlternativeTitle> alternativeTitles = publicationPreprocessor.convertMapToAlternativeTitle(alternativeTitleList);
                List<String> publishers = publicationPreprocessor.convertMapToPublisher(publisherList, labelList);
                publisherRelation.setLabelPublisherMap(publicationPreprocessor.createPublisherRelations(publisherList, labelList));

                retrieveList.add(
                        //author, artist는 아직 data가 없어서 안넣을거임.
                        Publication.builder()
                                .title(title)
                                .description(description)
                                .typeId(typeId)
                                .published_date(year)
                                .statusInOriginCountry(volumeStatus.getVolume())
                                .status(volumeStatus.getStatus())
                                .coverImageUrl(url)
                                .relatedSeriesList(relatedSeries)
                                .alternativeTitleList(alternativeTitles)
                                .publisherList(publishers)
                                .genres(genres)
                                .animeAdaptationList(animeAdaptationList)
                                .build()
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return retrieveList;
    }

    /**
     * call after readCSV
     * @return
     */
    public PublisherRelation getPublisherRelation() {
        return publisherRelation;
    }
}
