package com.aniDB.aniDB_batch.mapper;

import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.entity.Publication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublicationMapper {
    void bulkInsertPublication(List<Publication> publicationList);

}
