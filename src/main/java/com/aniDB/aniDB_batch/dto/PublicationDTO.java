package com.aniDB.aniDB_batch.dto;

import com.aniDB.aniDB_batch.entity.AlternativeTitle;
import com.aniDB.aniDB_batch.entity.AnimeAdaptation;
import com.aniDB.aniDB_batch.entity.Genre;
import com.aniDB.aniDB_batch.entity.RelatedSeries;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PublicationDTO {

    private Long publicationId;
    private String title;
    private String description;
    private int seriesType;
    @Builder.Default
    private int statusInOriginCountry = 0;
    @Builder.Default
    private String status = "Ongoing"; // Default value "Ongoing"
    private LocalDate published_date;
    @Builder.Default
    private boolean licensed = false; // Default value false
    @Builder.Default
    private int ranked = 0;
    @Builder.Default
    private int upvotes = 0;
    @Builder.Default
    private double scores = 0.0; // Default value 0.0
    @Builder.Default
    private int followedBy = 0;
    private String coverImageUrl;

    private List<AlternativeTitle> alternativeTitleList;
    private List<RelatedSeries> relatedSeriesList;
    private List<Genre> genres;
    private List<AnimeAdaptation> animeAdaptationList;
}
