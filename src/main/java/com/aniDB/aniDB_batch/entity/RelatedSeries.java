package com.aniDB.aniDB_batch.entity;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RelatedSeries {
    private  Long relatedPublicationId;
    private  Long publicationId;
    private  String relation;
}