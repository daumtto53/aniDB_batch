package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.SeriesType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SeriesTypeMapper {
    void insertAllSeriesType(List<SeriesType> seriesTypeList);
    List<Map<String, Object>> selectAllSeriesType();
}
