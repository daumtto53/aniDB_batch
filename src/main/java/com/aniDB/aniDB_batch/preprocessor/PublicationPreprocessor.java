package com.aniDB.aniDB_batch.preprocessor;

import com.aniDB.aniDB_batch.dto.GsonAnimeAdaptationBinder;
import com.aniDB.aniDB_batch.dto.GsonPublisherBinder;
import com.aniDB.aniDB_batch.entity.*;
import com.aniDB.aniDB_batch.service.GenreService;
import com.aniDB.aniDB_batch.service.SeriesTypeService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
//@RequiredArgsConstructor
@Log4j2
public class PublicationPreprocessor {
    private final GenreService genreService;
    private final SeriesTypeService seriesTypeService;
    private Map<String, Integer> genreMap;
    private Map<String, Integer> seriesTypeMap;

    public PublicationPreprocessor(GenreService genreService, SeriesTypeService seriesTypeService) {
        this.genreService = genreService;
        this.seriesTypeService = seriesTypeService;
        this.genreMap = genreService.getAllGenres();
        this.seriesTypeMap = seriesTypeService.getSeriesTypeAsMap();

    }

    Gson gson = new Gson();

    public int mapTypeToInt(String type) {
        try {
            Integer typeNum = seriesTypeMap.get(type.strip());
            return typeNum;
        } catch (NullPointerException e) {
            log.error("no type: type={}" , type);
            return 1;
        }
    }

    public LocalDateTime yearStringToLocalDateTime(String year) {
        if (year.equals("")) {
            return null;
        }
        return LocalDateTime.of(Integer.valueOf(year), 1, 1, 12, 0, 0);
    }

    public List<Map<String, String>> getRealatedSeriesList(String relatedSeriesJsonString) {
        Type listType = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        try {
            List<Map<String, String>> relatedSeriesMapList = (List<Map<String, String>>) gson.fromJson(relatedSeriesJsonString, listType);
            return relatedSeriesMapList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Map<String, String>> getAlternativeTitleList(String alternativeTitleJsonString) {
//        System.out.println(alternativeTitleJsonString);
        try {
            Type listType = new TypeToken<List<Map<String, String>>>() {
            }.getType();
            List<Map<String, String>> alternativeTitleMapList = (List<Map<String, String>>) gson.fromJson(alternativeTitleJsonString, listType);
            return alternativeTitleMapList;
        } catch (JsonSyntaxException e) {
//            log.info("alternativeTitle STring = {}, e = {}", alternativeTitleJsonString, e);
            return new ArrayList<>();
        }
    }

    public VolumeStatus getVolumeStatus(String volumeStatusJsonString) {
//        volumeStatusJsonString.replace("\"", "'");
        try {
            // Parse JSON to VolumeStatus object
            VolumeStatus volumeStatus = gson.fromJson(volumeStatusJsonString, VolumeStatus.class);

            // Print the result
            return volumeStatus;
        } catch (JsonSyntaxException e) {
            System.err.println("Failed to parse JSON: " + e.getMessage());
            return VolumeStatus.builder().volume(-1).build();
        }
    }

    public String getImageURL(String url) {
        return url.equals("") ? null : url;
    }


    public List<String> getAuthorList(String jsonString) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        try {
            List<String> authorList = gson.fromJson(jsonString, listType);
            return authorList;
        } catch (JsonSyntaxException | IllegalStateException e) {
            return new ArrayList<>();
        }
    }

    public List<String> getArtistList(String jsonString) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        List<String> artistList = gson.fromJson(jsonString, listType);
        return artistList;
    }

    public List<Map<String, String>> getPublisherList(String jsonString) {
        Type listType = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        List<Map<String, String>> publishers = gson.fromJson(jsonString, listType);
        return publishers;
    }

    public List<String> getGenreList(String genreJsonString) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        List<String> genres = gson.fromJson(genreJsonString, listType);
        return genres;
    }

    public List<AnimeAdaptation> getAnimeAdaptationList(String animeAdaptationJsonString) {
        String jsonString = animeAdaptationJsonString.replace("'", "\"");
        Type listType = new TypeToken<List<GsonAnimeAdaptationBinder>>() {
        }.getType();
        List<GsonAnimeAdaptationBinder> adaptMapList = gson.fromJson(jsonString, listType);
        List<AnimeAdaptation> collect = adaptMapList.stream()
                .map(element -> {
                    Integer volStart = element.getVolume_start().equals("") ? null : Integer.valueOf(element.getVolume_start());
                    Integer volEnd = element.getVolume_end().equals("") ? null : Integer.valueOf(element.getVolume_end());
                    Integer animeStart = Integer.valueOf(element.getAnime_start());
                    Integer animeEnd = Integer.valueOf(element.getAnime_end());

                    return AnimeAdaptation.builder()
                            .publicationStart(volStart)
                            .publicationEnd(volEnd)
                            .animeStart(animeStart)
                            .animeEnd(animeEnd)
                            .build();
                }).collect(Collectors.toList());
        return collect;
    }

    public List<Genre> getGenreIdList(List<String> genreList) {
        List<Genre> collect;
        try {
            collect = genreList.stream()
                    .map(genreString -> Genre.builder()
                            .genreId(Long.valueOf(genreMap.get(genreString)))
                            .genreName(genreString)
                            .build()).collect(Collectors.toList());
        } catch (Exception e) {
            collect = new ArrayList<>();
        }
        return collect;
    }

    public List<RelatedSeries> convertMapToRelatedSeries(List<Map<String, String>> relatedSeriesList) {
        List<RelatedSeries> relations = relatedSeriesList.stream().map(map -> {
            return RelatedSeries.builder()
                    .title(map.get("title"))
                    .relation(map.get("relation")).build();
        }).collect(Collectors.toList());
        return relations;
    }

    public List<AlternativeTitle> convertMapToAlternativeTitle(List<Map<String, String>> alternativeTitleList) {
        List<AlternativeTitle> alternativeTitles = alternativeTitleList.stream().map(map -> {
            return AlternativeTitle.builder()
                    .alternativeTitle(map.get("title"))
                    .language(map.get("language"))
                    .build();
        }).collect(Collectors.toList());
        return alternativeTitles;
    }

    public List<String> convertMapToPublisher(List<Map<String, String>> publisherList, List<Map<String, String>> labelList) {
        Set<String> publishers = new HashSet<>();
        publisherList.stream()
                .forEach(map -> {
                    if (!map.get("publisher").equals("")){
                        publishers.add(map.get("publisher"));
                    }
                    if (!map.get("label").equals("")){
                        publishers.add(map.get("label"));
                    }
                });
        labelList.stream()
                .forEach(map -> {
                    if (!map.get("publisher").equals("")){
                        publishers.add(map.get("publisher"));
                    }
                    if (!map.get("label").equals("")){
                        publishers.add(map.get("label"));
                    }
                });
        List<String> nonDuplicate = new ArrayList<>();
        nonDuplicate.addAll(publishers);
        return nonDuplicate;
    }

    public Map<String, String> createPublisherRelations(Map<String, String> labelPublisherMap, List<Map<String, String>> publisherList, List<Map<String, String>> labelList) {
        publisherList.stream()
                .forEach(map -> {
                    labelPublisherMap.put(map.get("label"), map.get("publisher"));
                });
        labelList.stream()
                .forEach(map -> {
                    labelPublisherMap.put(map.get("label"), map.get("publisher"));
                });
        return labelPublisherMap;
    }
}
