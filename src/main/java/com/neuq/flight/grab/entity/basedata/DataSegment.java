package com.neuq.flight.grab.entity.basedata;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: xiaoliufu
 * Date: 3/9/17
 */
@Data
public class DataSegment {
    private String Name;
    private int Type;
    private String EName;
    private String Spell;
    private String ShortSpell;
    private String Country;
    private String Province;
    private String Code;
    private String Dist;
    private int CityId;
    private List<AirportdataSegment> Datas;
    private String TimeZone;
    private String POIID;
}
