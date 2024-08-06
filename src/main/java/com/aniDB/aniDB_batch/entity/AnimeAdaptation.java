package com.aniDB.aniDB_batch.entity;

import lombok.*;

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
    private  String publicationStart;
    private  String publicationEnd;
    private  String animeType;
    private  String animeStart;
    private  String animeEnd;
}
