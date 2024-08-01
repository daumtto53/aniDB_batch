package com.aniDB.aniDB_batch.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlternativeTitle {

    private Long alternativeTitleId;
    private Long publicationId;
    private String alternativeTitle;
    private String language;
}


