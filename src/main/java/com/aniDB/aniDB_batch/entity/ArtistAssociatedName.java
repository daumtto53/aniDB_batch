package com.aniDB.aniDB_batch.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArtistAssociatedName {
    private Long associatedNameId;
    private Long artistId;
    private String associatedName;
}
