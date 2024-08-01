package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.repository.GenreRepository;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class ReadCSVGenreImpl implements ReadCSV<Genre>{

    @Override
    public List<Genre> readCSV(List<Genre> retrieveList, String csvPath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))){
            String line[];
            Gson gson = new Gson();

            while ((line = reader.readNext()) != null) {
                log.info("line[0]={}", line[0]);
                retrieveList.add(Genre.builder().genreName(line[0]).build());
            }
//            genreRepository.addGenres(retrieveList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return retrieveList;
    }
}
