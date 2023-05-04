package com.crm.low_crm.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AsyncResponseDto {
    private Object result;
    private Integer percent;
    private boolean error;
    private String errorReason;
}
