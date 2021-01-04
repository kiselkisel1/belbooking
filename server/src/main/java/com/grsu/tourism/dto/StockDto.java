package com.grsu.tourism.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    private Integer id;
    private Double discount;
    private Date beginDate;
    private Date endDate;

    //оставлю пока так,зато в service.stocks можно сделать  такую панельку типо
    //похожие акции и для этой услуги взять еще акции
    private ServiceDto service;
}
