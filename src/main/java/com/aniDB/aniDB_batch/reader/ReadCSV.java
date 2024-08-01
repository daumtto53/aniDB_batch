package com.aniDB.aniDB_batch.reader;

import java.util.List;

/*
    CSV READING ORDER
    1. TITLE
    2. Description
    3. TYPE
    4. YEAR
    5. related_series - [{'title': '', 'type': '', relation: ''}, ....]
    6. alternative_title - [{'title': '' , language: ''}, ...]
    7. status_in_origin_country - {'volume': INT, 'status': ''}
    8. IMAGE_ URL
    9. ARTISTS - []
    10. AUTHORS - []
    11. ORIGINAL_PUBLISHER - [{publisher, label}]
    12. SERIALIZED_IN - [{publisher, label}]
    13. GENRES - []
    14. anime_start_and_end - [{volume_start:'', anime_start:1, volume_end:'', anime_end:1 }, ...]
    15.
 */
public interface ReadCSV<T> {

    public List<T> readCSV(List<T> retrieveList, String csvPath);
}
