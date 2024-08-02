package com.aniDB.aniDB_batch.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Publisher {
    private  Long publisherId;
    private  String publisherName;
    private  String websiteUrl;
//    private  String parentPublisher;
    private  List<String> alternativePublisherNameList;
}