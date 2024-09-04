package com.aniDB.aniDB_batch.processor;

import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publisher;
import com.aniDB.aniDB_batch.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class PublisherProcessor {
    private final PublisherService publisherService;

    @Value("${profile.dev.csv-location}")
    String csvPath;

    public void bulkInsertPublisher() {
        List<Publisher> publisherList = new ArrayList<>();
        List<AlternativePublisherName> alternativePublisherNameList = new ArrayList<>();
        publisherService.bulkInsertPublisher(publisherList, alternativePublisherNameList ,csvPath + "publishers.csv");
    }
}
