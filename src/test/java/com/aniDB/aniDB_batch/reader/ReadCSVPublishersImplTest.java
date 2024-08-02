package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.Publication;
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
class ReadCSVPublishersImplTest {

    @Autowired
    ReadCSVPublishersImpl readCSVPublishers;
    @Value("${profile.dev.csv-location}")
    String csvPath;

    @Test
    void readCSV() {
        List<Publisher> publisherList= new ArrayList<>();
        readCSVPublishers.readCSV(publisherList, csvPath + "publishers.csv");
    }
}