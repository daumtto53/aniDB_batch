package com.aniDB.aniDB_batch.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublicationPublisher {
    private Long publicationId;
    private Long publisherId;
}
