package com.aniDB.aniDB_batch.dto;

import lombok.*;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GsonAnimeAdaptationBinder {
    private String volume_start;
    private String anime_start;
    private String volume_end;
    private String anime_end;
}
