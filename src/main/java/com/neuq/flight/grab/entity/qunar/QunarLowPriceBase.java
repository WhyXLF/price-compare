package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

/**
 * author: xiaoliufu
 * date:   2016/12/31.
 * description:
 */
@Data
public class QunarLowPriceBase {
    private String cabin;
    private String wrapperId;
    private String bookingInfo;
    private int basePrice;
    private String totalCabin;
    private String totalWrapperId;
    private String totalBookingInfo;
}
