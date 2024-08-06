package com.aniDB.aniDB_batch.repository;

import com.aniDB.aniDB_batch.entity.AlternativeTitle;
import com.aniDB.aniDB_batch.mapper.AlternativeTitleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class AlternativeTitleRepository {
    private final AlternativeTitleMapper alternativeTitleMapper;

    @Transactional
    public void bulkInsertAlternativeTitle(List<AlternativeTitle> alternativeTitleList) {
        alternativeTitleMapper.bulkInsertAlternativeTitle(alternativeTitleList);
    }
}
