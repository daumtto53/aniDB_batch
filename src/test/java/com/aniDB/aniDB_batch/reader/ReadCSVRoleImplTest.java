package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("dev")
class ReadCSVRoleImplTest {

    @Autowired
    ReadCSVRoleImpl readCSVRole;
    @Value("${profile.dev.csv-location}")
    String csvPath;

    @Test
    void readCSV() {
        System.out.println(csvPath);
        List< Role> roleList = new ArrayList<>();
        readCSVRole.readCSV(roleList, csvPath + "role.csv");
    }
}