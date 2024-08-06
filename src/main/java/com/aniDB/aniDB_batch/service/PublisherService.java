package com.aniDB.aniDB_batch.service;


import com.aniDB.aniDB_batch.dto.PublisherRelationDTO;
import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publisher;
import com.aniDB.aniDB_batch.reader.ReadCSV;
import com.aniDB.aniDB_batch.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PublisherService {
    private final ReadCSV<Publisher> readCSV;
    private final PublisherRepository publisherRepository;


    public void bulkInsertPublisher(List<Publisher> publisherList,
                                    List<AlternativePublisherName> alternativePublisherNameList,
                                    String path){
        readCSV.readCSV(publisherList, path);
        // publisherList의 publisher에 publisherId가 들어옴.
        publisherRepository.bulkInsertPublisher(publisherList);

        publisherList.stream().forEach(publisher -> {

            for (String alternateName: publisher.getAlternativePublisherNameList()) {
                alternativePublisherNameList.add(
                        AlternativePublisherName.builder()
                                .originalPublisherId(publisher.getPublisherId())
                                .alternativeName(alternateName)
                                .build()
                );
            }
        });
        // alternative_publisher_name에 original_publisher_id, alternative_name을 저장.
        publisherRepository.bulkInsertAlternativePublisherName(alternativePublisherNameList);
    }

    public void updatePublisherForLabelPublisherRelation(List<PublisherRelationDTO> publisherRelationDTOList) {
        publisherRepository.updatePublisherForLabelPublisherRelation(publisherRelationDTOList);
    }
}
