package com.aniDB.aniDB_batch.dto;

import lombok.*;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GsonPublisherBinder {
    private String publisher;
    private String label;
}
