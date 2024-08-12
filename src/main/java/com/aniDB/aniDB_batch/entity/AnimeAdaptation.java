package com.aniDB.aniDB_batch.entity;

import lombok.*;
import org.springframework.data.relational.core.sql.In;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnimeAdaptation {
    private  Long animeAdaptationId;
    private  Long publicationId;
    private  Long animeId;
    private  Integer publicationStart;
    private  Integer publicationEnd;
    private  Integer animeType;
    private  Integer animeStart;
    private  Integer animeEnd;
}
