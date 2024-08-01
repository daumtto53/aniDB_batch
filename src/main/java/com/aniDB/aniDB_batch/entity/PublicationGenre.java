package com.aniDB.aniDB_batch.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PublicationGenre {
    private Long titleId;
    private Long genreId;
}
