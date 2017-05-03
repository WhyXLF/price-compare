package com.neuq.flight.grab.entity.basedata;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: xiaoliufu
 * Date: 3/9/17
 */
@Data
public class CityInfoResponse {
    private int Status;
    private String key;
    private List<DataSegment> Data;
}
