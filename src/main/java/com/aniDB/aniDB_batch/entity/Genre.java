package com.aniDB.aniDB_batch.entity;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Genre {
    private Long genreId;
    private String genreName;
    private String description;
}
