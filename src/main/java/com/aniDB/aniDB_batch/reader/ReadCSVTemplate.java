package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.Publication;
import com.google.gson.Gson;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.List;

public class ReadCSVTemplate implements ReadCSV<Publication> {
    @Override
    public List<Publication> readCSV(List<Publication> retrieveList, String csvPath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))){
            String line[];
            Gson gson = new Gson();

            while ((line = reader.readNext()) != null) {
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
