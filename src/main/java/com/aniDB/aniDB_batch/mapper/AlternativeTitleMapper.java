package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.AlternativeTitle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlternativeTitleMapper {
    void bulkInsertAlternativeTitle(List<AlternativeTitle> alternativeTitleList);
}
