package com.crm.low_crm.model.dto;

import com.crm.low_crm.model.enumerate.Function;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShowValues implements Serializable {
    /*"name": "string", "func":"string"*/
    private Boolean disabled;
    private String name;
    private Function func;
    private String type;
    private String axes;
    private String color;
}
