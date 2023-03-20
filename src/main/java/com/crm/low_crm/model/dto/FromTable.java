package com.crm.low_crm.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FromTable  implements Serializable {
    /*{"name": "string",
    "main":"boolean",
     "joinTable":"string",
      "columnParent":"string",
       "columChild":"string",
        "joinType":"string"*/
    private String name;
    private Boolean main;
    private String joinTable;
    private String columnParent;
    private String columnChild;
    private String joinType;
}
