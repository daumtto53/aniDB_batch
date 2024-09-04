package com.aniDB.aniDB_batch.processor;

import com.aniDB.aniDB_batch.entity.SeriesType;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import com.aniDB.aniDB_batch.service.SeriesTypeService;
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
public class SeriesTypeProcessor {
    private final SeriesTypeService seriesTypeService;
    private final ReadCSV<SeriesType> seriesTypeReadCSV;

    @Value("${profile.dev.csv-location}")
    String csvPath;

    public void insertAllSeriesType() {
        List<SeriesType> seriesTypeList = new ArrayList<>();
        seriesTypeReadCSV.readCSV(seriesTypeList, csvPath + "series_type.csv");
        seriesTypeService.saveSeriesTypeFromCSV(seriesTypeList);
    }
}
