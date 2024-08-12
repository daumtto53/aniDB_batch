package com.aniDB.aniDB_batch.service;

import com.aniDB.aniDB_batch.dto.PublisherRelationDTO;
import com.aniDB.aniDB_batch.entity.*;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import com.aniDB.aniDB_batch.reader.ReadCSVPublicationImpl;
import com.aniDB.aniDB_batch.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PublicationService {

    private final ReadCSV<Publication> readCSV;
    private final PublicationRepository publicationRepository;
    private final PublisherRepository publisherRepository;
    private final AlternativeTitleRepository alternativeTitleRepository;
    private final AnimeAdaptationRepository animeAdaptationRepository;
    private final PublicationPublisherRepository publicationPublisherRepository;
    private final PublicationGenreRepository publicationGenreRepository;
    private final RelatedSeriesRepository relatedSeriesRepository;

    public void bulkInsertPublication(List<Publication> publicationList,
                                      List<AlternativeTitle> alternativeTitleList,
                                      List<RelatedSeries> relatedSeriesList,
                                      List<PublicationPublisher> publicationPublisherList,
                                      List<PublicationGenre> publicationGenreList,
                                      List<AnimeAdaptation> animeAdaptationList,
                                      String path) {
        ReadCSVPublicationImpl readCSVPublication = (ReadCSVPublicationImpl) readCSV;
        //publicationList 저장되어있음.
        readCSVPublication.readCSV(publicationList, path);

        //publication 저장하기 전에 publisher 리스트 다시 한번 넣어주기. (Label 때문에.)
        List<Publisher> publisherList = new ArrayList<>();
        preparePublisherListForBulkInserting(publicationList, publisherList);
        publisherRepository.bulkInsertPublisherWithLabel(publisherList);

        // publication 저장.
        publicationRepository.bulkInsertPublication(publicationList);
        //publicationList에 id가 저장되어있다.
        Map<String, String> labelPublisherMap = ((ReadCSVPublicationImpl) readCSV).getPublisherRelation().getLabelPublisherMap();
        //Add labels;
//        insertLabels(publicationList);
        updatePublisherRelations(labelPublisherMap);


        /*
         * 저장해야 될 tables
         * 1. alternative_ttile
         *
         * 2. related_series
         * 3. publication_publisher
         *       parent_publisher 활용하기.
         * 4. publication_genre
         * 5. anime_adaptation
         *
         * 원래는 ARTIST, AUTHOR도 있어야되는데.. 관련 데이터가 없으니 일단은 철회ㅣ.
         * */
        setAlternativeTitleList(publicationList, alternativeTitleList);
        setRelatedSeriesList(publicationList, relatedSeriesList);
        setPublicationPublisherList(publicationList, publicationPublisherList);
        setPublicationGenreList(publicationList, publicationGenreList);
        setAnimeAdaptationList(publicationList, animeAdaptationList);
        System.out.println("check");
        relatedSeriesRepository.bulkInsertRelatedSeries(relatedSeriesList);
        publicationPublisherRepository.bulkInsertPublicationPublisher(publicationPublisherList);
        publicationGenreRepository.bulkInsertPublicationGenre(publicationGenreList);
        animeAdaptationRepository.bulkInsertAnimeAdaptation(animeAdaptationList);
        alternativeTitleRepository.bulkInsertAlternativeTitle(alternativeTitleList);
    }

    private void preparePublisherListForBulkInserting(List<Publication> publicationList, List<Publisher> publisherList) {
        publicationList.stream().forEach(publication -> {
            List<String> publisherStringList  = publication.getPublisherList();
            for (String publisherString: publisherStringList){
                publisherList.add(
                        Publisher.builder().publisherName(publisherString).build()
                );
            }
        });
    }


    private void setAlternativeTitleList(List<Publication> publicationList,
                                        List<AlternativeTitle> alternativeTitleList) {
        publicationList.stream()
                .forEach(publication -> {

                    for (AlternativeTitle alternativeTitle : publication.getAlternativeTitleList()) {
                        AlternativeTitle createdEntity = AlternativeTitle.builder()
                                .publicationId(publication.getPublicationId())
                                .alternativeTitle(alternativeTitle.getAlternativeTitle())
                                .language(alternativeTitle.getLanguage())
                                .build();
                        alternativeTitleList.add(createdEntity);
                    }
                });
    }

    private void setRelatedSeriesList(List<Publication> publicationList,
                                     List<RelatedSeries> relatedSeriesList) {
        publicationList.stream()
                .forEach(publication -> {

                    for (RelatedSeries relatedSeries : publication.getRelatedSeriesList()) {
                        RelatedSeries createdEntity = RelatedSeries.builder()
                                .publicationId(publication.getPublicationId())
                                .relation(relatedSeries.getRelation())
                                .build();
                        relatedSeriesList.add(createdEntity);
                    }
                });
    }

    private void setPublicationPublisherList(List<Publication> publicationList,
                                            List<PublicationPublisher> publicationPublisherList) {
        Map<String, Integer> publisherMapOfNameAndId = publisherRepository.selectPublisherMapOfNameAndId();

        for (Map.Entry<String, Integer> entry: publisherMapOfNameAndId.entrySet()) {
            log.info("key = {}, value = {}", entry.getKey(), entry.getValue());
        }

        publicationList.stream()
                .forEach(publication-> {
                    for (String publisherName: publication.getPublisherList()) {
                        log.info("publisherName = {}, publication.getId= {}", publisherName, publisherMapOfNameAndId.get(publisherName));
                        if (publisherMapOfNameAndId.get(publisherName) == null)
                            continue;
                        PublicationPublisher createdEntity = PublicationPublisher.builder()
                                .publicationId(publication.getPublicationId())
                                .publisherId(
                                        Long.valueOf(publisherMapOfNameAndId.get(publisherName))
                                )
                                .build();
                        publicationPublisherList.add(createdEntity);
                    }
                });
    }

    private void setPublicationGenreList(List<Publication> publicationList,
                                        List<PublicationGenre> publicationGenreList) {
        publicationList.stream()
                .forEach(publication -> {
                    for (Genre genre : publication.getGenres()) {
                        publicationGenreList.add(
                                PublicationGenre.builder()
                                        .publicationId(publication.getPublicationId())
                                        .genreId(genre.getGenreId())
                                        .build()
                        );
                    }
                });
    }

    private void setAnimeAdaptationList(List<Publication> publicationList,
                                       List<AnimeAdaptation> animeAdaptationList) {
        publicationList.stream()
                .forEach(publication -> {
                    for (AnimeAdaptation animeAdaptation : publication.getAnimeAdaptationList()) {
                        animeAdaptationList.add(
                                AnimeAdaptation.builder()
                                        .publicationId(publication.getPublicationId())
                                        .publicationStart(animeAdaptation.getPublicationStart())
                                        .publicationEnd(animeAdaptation.getPublicationEnd())
                                        .animeStart(animeAdaptation.getAnimeStart())
                                        .animeEnd(animeAdaptation.getAnimeEnd())
                                        .build()
                        );
                    }
                });
    }

    private void updatePublisherRelations(Map<String, String> labelPublisherMap) {

        //labelPublisherMap에서 publisher가 ''인것 제외
        labelPublisherMap.entrySet().stream()
                .filter(entry -> !entry.getValue().equals(""))
                .filter(entry -> !entry.getKey().equals(""))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        //모든 publisher의 Map의 name, id를 반환.
        Map<String, Integer> publisherMapOfNameAndId = publisherRepository.selectPublisherMapOfNameAndId();

        //Bulk UPdate를 위한 publisherUpdateDTO를 생성한다.
        List<PublisherRelationDTO> publisherRelationDTOList = labelPublisherMap.entrySet().stream()
                .map(entry -> {
                    return PublisherRelationDTO.builder()
                            .labelName(entry.getKey())
                            .parentPublisherId(publisherMapOfNameAndId.get(entry.getValue()))
                            .build();
                }).collect(Collectors.toList());
        //bulkUpdate를 한다.

        for (PublisherRelationDTO publisherRelationDTO: publisherRelationDTOList) {
            publisherRepository.updatePublisherForLabelPublisherRelationTest(publisherRelationDTO);
        }
//        publisherRepository.updatePublisherForLabelPublisherRelation(publisherRelationDTOList);
        System.out.println("nothing");
    }


}
