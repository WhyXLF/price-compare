package com.neuq.flight.grab.constant.enumType;

/**
 * Created by IntelliJ IDEA.
 * Author: xiaoliufu
 * Date: 3/16/17
 */
public enum TripType {
    OW(0), RT(1);
    TripType(int tripType) {
        this.tripType = tripType;
    }
    int tripType;

    public int getTripType() {
        return tripType;
    }

    public void setTripType(int tripType) {
        this.tripType = tripType;
    }

    @Override
    public String toString() {
        return "TripType{" +
                "tripType=" + tripType +
                '}';
    }
}
