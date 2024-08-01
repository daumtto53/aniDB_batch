package com.aniDB.aniDB_batch.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Artist {
    private  Long artistId;
    private  int artistType;
    private  String nativeName;
    private  String description;
    private  String birthPlace;
    private LocalDate birthDate;
    private  String status;
    private  String officialWebsiteUrl;
    private  String twitterUrl;
    private  String genres;
    private  String gender;
    private  String coverImageUrl;

    private List<ArtistType> artistTypeList;
    private List<ArtistAssociatedName> associatedNameList;
}