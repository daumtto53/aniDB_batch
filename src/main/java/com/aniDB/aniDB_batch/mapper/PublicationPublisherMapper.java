package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.PublicationPublisher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublicationPublisherMapper {
    void bulkInsertPublicationPublisher(List<PublicationPublisher> publicationPublisherList);
}
