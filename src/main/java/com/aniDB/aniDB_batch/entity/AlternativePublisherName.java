package com.aniDB.aniDB_batch.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlternativePublisherName {
    private Long alternativePublisherId;
    private Long originalPublisherId;
    private String alternativeName;
}