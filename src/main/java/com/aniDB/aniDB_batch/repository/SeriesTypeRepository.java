package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.SeriesType;
import com.aniDB.aniDB_batch.mapper.SeriesTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Log4j2
@RequiredArgsConstructor
public class SeriesTypeRepository {
    private final SeriesTypeMapper seriesTypeMapper;

    public void saveAllSeriesType(List<SeriesType> seriesTypeList) {
        seriesTypeMapper.insertAllSeriesType(seriesTypeList);
    }

    public List<Map<String, Object>> selectAllSeriesType() {
        return seriesTypeMapper.selectAllSeriesType();
    }



}
