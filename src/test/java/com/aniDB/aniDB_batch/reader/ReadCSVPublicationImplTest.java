package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.Publication;
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
class ReadCSVPublicationImplTest {

    @Value("${profile.dev.csv-location}")
    String csvPath;
    @Autowired
    ReadCSVPublicationImpl publicationReadCSV;

    @Test
    void readCSV() {
        List<Publication> publicationList = new ArrayList<>();
        publicationReadCSV.readCSV(publicationList, csvPath + "novel_info.csv");
        System.out.println("");
    }

    @Test
    void publisherRelation() {
        List<Publication> publicationList = new ArrayList<>();
        publicationReadCSV.readCSV(publicationList, csvPath + "novel_info.csv");
        publicationReadCSV.getPublisherRelation();
        System.out.println("");
    }
}