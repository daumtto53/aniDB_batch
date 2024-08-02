package com.aniDB.aniDB_batch.service;

import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class PublisherServiceTest {

    @Autowired
    PublisherService publisherService;

    @Value("${profile.dev.csv-location}")
    String csvPath;

    @Test
    void bulkInsertPublisher() {
        List<Publisher> publisherList = new ArrayList<>();
        List<AlternativePublisherName> alternativePublisherNameList = new ArrayList<>();
        publisherService.bulkInsertPublisher(publisherList, alternativePublisherNameList ,csvPath + "publishers.csv");
    }
}