package com.aniDB.aniDB_batch.service;

import com.aniDB.aniDB_batch.entity.SeriesType;
import com.aniDB.aniDB_batch.repository.SeriesTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class SeriesTypeService {
    private final SeriesTypeRepository seriesTypeRepository;

    @Transactional
    public void saveSeriesTypeFromCSV(List<SeriesType> seriesTypeList) {
        seriesTypeRepository.saveAllSeriesType(seriesTypeList);
    }

    public Map<String, Integer> getSeriesTypeAsMap() {
        List<Map<String, Object>> maps = seriesTypeRepository.selectAllSeriesType();
        Map<String , Integer > convertedMap = maps.stream()
                .collect(Collectors.toMap(map -> (String) map.get("type_name"), map -> (Integer) map.get("type_id")));
        return convertedMap;
    }
}
