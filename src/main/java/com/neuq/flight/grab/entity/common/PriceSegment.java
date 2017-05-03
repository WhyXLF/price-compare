package com.neuq.flight.grab.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * author: xiaoliufu
 * date:   2017/1/24.
 * description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceSegment {
    /**
     * 航司
     */
    private String carrier;
    /**
     * 航班号
     */
    private String flightNumber;
    /**
     * 出发机场
     */
    private String depAirport;
    /**
     * 出发时间：日期时间
     */
    @JSONField(format = "yyyyMMddHHmm")
    private Date depTime;
    /**
     * 到达机场
     */
    private String arrAirport;
    /**
     * 到达时间
     */
    @JSONField(format = "yyyyMMddHHmm")
    private Date arrTime;
    /**
     * 停靠城市，多个值用/分隔
     */
    private String stopCities;
    /**
     * 是否代码共享
     */
    private boolean codeShare;
    /**
     * 共享航班的代码
     */
    private String sharingFlightNumber;
    /**
     * 出发航站楼
     */
    private String departureTerminal;
    /**
     * 到达航站楼
     */
    private String arrivingTerminal;
    /**
     * 舱位
     */
    private String cabin;
    /**
     * 飞机型号
     */
    private String aircraftCode;
    /**
     * 座位数量
     */
    private String seatCount;
    /**
     * 舱位等级
     */
    private Integer cabinClass;

}
