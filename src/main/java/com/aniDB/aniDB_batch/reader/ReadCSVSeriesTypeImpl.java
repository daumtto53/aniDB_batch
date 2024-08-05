package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.SeriesType;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.List;

@Component
@Log4j2
public class ReadCSVSeriesTypeImpl implements ReadCSV<SeriesType> {
    @Override
    public List<SeriesType> readCSV(List<SeriesType> retrieveList, String csvPath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))){
            String line[];

            while ((line = reader.readNext()) != null) {
                retrieveList.add(SeriesType.builder()
                        .typeName(line[0].strip())
                        .build()
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return retrieveList;
    }
}
