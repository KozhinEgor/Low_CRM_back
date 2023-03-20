package com.crm.low_crm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Report implements Serializable {

    private Long id;
    private String name;
    private String name_en;
}
