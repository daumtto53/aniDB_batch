package com.aniDB.aniDB_batch.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
public class PublisherRelation {
    private Map<String, String> labelPublisherMap;

    public PublisherRelation() {
        this.labelPublisherMap = new HashMap<>();
    }

}
