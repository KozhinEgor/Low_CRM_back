package com.crm.low_crm.model.view;

import com.crm.low_crm.configuration.MySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonSerialize(using = MySerializer.class)
public class ViewReport {
    private Integer count;
    private Integer secondCount;
    private LocalDate date;
    private Integer year;
    private Integer week;
    private Integer month;
}
