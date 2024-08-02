package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.Role;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.List;

@Component
@Log4j2
public class ReadCSVRoleImpl implements ReadCSV<Role>{
    @Override
    public List<Role> readCSV(List<Role> retrieveList, String csvPath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))){
            String line[];
            Gson gson = new Gson();

            while ((line = reader.readNext()) != null) {
//                log.info("line[0] = {}", line[0]);
                retrieveList.add(Role.builder().
                        roleName(line[0].strip())
                        .build()
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return retrieveList;
    }
}
