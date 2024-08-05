package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.SeriesType;
import com.aniDB.aniDB_batch.service.SeriesTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class ReadCSVSeriesTypeImplTest {
    @Autowired
    ReadCSV<SeriesType> seriesTypeReadCSV;
    @Autowired
    SeriesTypeService seriesTypeService;
    @Value("${profile.dev.csv-location}")
    String csvPath;

    @Test
    void readCSV() {
        List<SeriesType> seriesTypeList = new ArrayList<>();
        seriesTypeReadCSV.readCSV(seriesTypeList, csvPath + "series_type.csv");
        System.out.println(seriesTypeList);
    }

}