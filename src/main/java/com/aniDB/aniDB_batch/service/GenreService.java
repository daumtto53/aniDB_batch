package com.aniDB.aniDB_batch.service;

import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import com.aniDB.aniDB_batch.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final ReadCSV<Genre> genreReadCSV;
    private final GenreRepository genreRepository;

    public void bulkinsertGenre(List<Genre> genreList, String path){
        genreReadCSV.readCSV(genreList, path);
        genreRepository.addGenres(genreList);
    }

    public Map<String, Integer> getAllGenres() {
        List<Map<String, Object>> maps = genreRepository.selectAllGenres();
        Map<String, Integer> convertedMap = maps.stream().collect(Collectors.toMap(
                map -> (String) map.get("genre_name"),
                map -> (Integer) map.get("genre_id")
        ));
        return convertedMap;
    }

}
