package com.aniDB.aniDB_batch.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PublisherRelationDTO {
    private String labelName;
    private Integer parentPublisherId;
}
