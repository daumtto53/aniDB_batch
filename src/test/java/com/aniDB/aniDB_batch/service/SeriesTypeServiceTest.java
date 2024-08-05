package com.aniDB.aniDB_batch.service;

import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publisher;
import com.aniDB.aniDB_batch.entity.SeriesType;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("dev")
class SeriesTypeServiceTest {

    @Autowired
    SeriesTypeService seriesTypeService;
    @Autowired
    ReadCSV<SeriesType> seriesTypeReadCSV;


    @Value("${profile.dev.csv-location}")
    String csvPath;


    @Test
    void insertAllSeriesType() {
        List<SeriesType> seriesTypeList = new ArrayList<>();
        seriesTypeReadCSV.readCSV(seriesTypeList, csvPath + "series_type.csv");
        seriesTypeService.saveSeriesTypeFromCSV(seriesTypeList);
    }

    @Test
    void getSeriesTypeAsMap() {
        Map<String, Integer> seriesTypeAsMap = seriesTypeService.getSeriesTypeAsMap();
        System.out.println(seriesTypeAsMap);
    }
}