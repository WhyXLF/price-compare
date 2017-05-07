package com.neuq.flight.grab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
    private Integer id;

    private Integer tripType;

    private String backDate;

    private String goDate;

    private String toCityCode;

    private String fromCityCode;

    private String searchInfo;
    
}