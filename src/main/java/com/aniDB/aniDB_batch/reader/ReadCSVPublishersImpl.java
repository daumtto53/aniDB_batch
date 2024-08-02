package com.aniDB.aniDB_batch.reader;

import com.aniDB.aniDB_batch.entity.AlternativePublisherName;
import com.aniDB.aniDB_batch.entity.Publication;
import com.aniDB.aniDB_batch.entity.Publisher;
import com.aniDB.aniDB_batch.entity.Role;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.*;

@Component
@Log4j2
public class ReadCSVPublishersImpl implements ReadCSV<Publisher> {

    /*
        1. name
        2. alternative_name, ['', '',]...
        3. type []
        4. website_url
     */
    @Override
    public List<Publisher> readCSV(List<Publisher> retrieveList, String csvPath) {

        try (CSVReader reader = new CSVReader(new FileReader(csvPath))){
            String line[];
            Gson gson = new Gson();

            while ((line = reader.readNext()) != null) {
                String publisherName;
                String type;
                String website_url;
                List<String> alternateNameList = new ArrayList<>();
//                log.info("1 = {}, 2= {}, 3= {}, 4= {}", line[0], line[1], line[2], line[3]);

                publisherName = line[0].strip();
                type = line[2].strip();
                website_url = line[3].strip();
                if (website_url.equals(""))
                    website_url = null;

                alternateNameList.add(publisherName);
                Pattern koreanPattern = Pattern.compile("[ㄱ-ㅣ가-힣]+");
                Pattern pattern = Pattern.compile("\'([^']*)\'");
                Matcher matcher = pattern.matcher(line[1]);
                while (matcher.find()) {
                    String alternateName = matcher.group(1).strip();
                    if (alternateName.equals("N/A"))
                        break;
                    alternateNameList.add(alternateName);
                    if (koreanPattern.matcher(matcher.group(1).strip()).find()){
                        publisherName = alternateName;
                    }
                }
                alternateNameList.remove(publisherName);
                retrieveList.add(
                        Publisher.builder()
                                .publisherName(publisherName)
                                .alternativePublisherNameList(alternateNameList)
                                .websiteUrl(website_url)
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
