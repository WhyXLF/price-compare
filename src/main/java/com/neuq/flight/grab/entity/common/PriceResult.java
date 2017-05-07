package com.neuq.flight.grab.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/1/24.
 * description: 报价结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceResult {
    /**
     * 报价信息
     */
    private List<PriceRouting> routings;


}
