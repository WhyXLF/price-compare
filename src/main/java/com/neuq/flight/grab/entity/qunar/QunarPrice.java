package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

/**
 * author: xiaoliufu
 * date:   2016/12/31.
 * description:
 */
@Data
public class QunarPrice {
    private int lowPrice;
    private int tax;
    private int taxType;
    private int lowTotalPrice;
    private int lowTotalTax;
    private int lowChildPrice;
    private int lowChildTax;
    private int childTaxType;
    private int totalTaxType;
    private String priceType;
    private String priceTag;
    private String currencyCode;
    private String totalPriceCode;
    private QunarLowPriceBase lowPriceBase;
}
