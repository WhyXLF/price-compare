package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

/**
 * author: xiaoliufu
 * date:   2016/12/29.
 * description:
 */
@Data
public class QunarResponse {
    private int status;
    private int cost;
    private QunarResult result;
}
