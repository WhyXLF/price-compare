package com.neuq.flight.grab.entity.basedata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * Author: xiaoliufu
 * Date: 3/9/17
 */
@Setter
public class AirportdataSegment {
    private String Name;
    private int Type;
    private String EName;
    private String Country;
    private String Province;
    private String Code;
    private int Dist;
    private int POIID;

    @JSONField(name = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @JSONField(name = "Type")
    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    @JSONField(name = "EName")
    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    @JSONField(name = "Country")
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @JSONField(name = "Province")
    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    @JSONField(name = "Code")
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @JSONField(name = "Dist")
    public int getDist() {
        return Dist;
    }

    public void setDist(int dist) {
        Dist = dist;
    }

    @JSONField(name = "POIID")
    public int getPOIID() {
        return POIID;
    }

    public void setPOIID(int POIID) {
        this.POIID = POIID;
    }

    @Override
    public String toString() {
        return "AirportdataSegment{" +
                "Name='" + Name + '\'' +
                ", Type=" + Type +
                ", EName='" + EName + '\'' +
                ", Country='" + Country + '\'' +
                ", Province='" + Province + '\'' +
                ", Code='" + Code + '\'' +
                ", Dist=" + Dist +
                ", POIID=" + POIID +
                '}';
    }
}
