package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publisher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {
    void bulkInsertPublisher(List<Publisher> publisherList);
    void bulkInsertAlternativePublisherName(List<AlternativePublisherName> alternativePublisherNameList);
}
