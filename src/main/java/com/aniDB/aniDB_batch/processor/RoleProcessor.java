package com.aniDB.aniDB_batch.processor;

import com.aniDB.aniDB_batch.entity.Role;
import com.aniDB.aniDB_batch.reader.ReadCSVRoleImpl;
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
public class RoleProcessor {
    private final ReadCSVRoleImpl readCSVRole;

    @Value("${profile.dev.csv-location}")
    String csvPath;

    void readCSV() {
        System.out.println(csvPath);
        List<Role> roleList = new ArrayList<>();
        readCSVRole.readCSV(roleList, csvPath + "role.csv");
    }
}
