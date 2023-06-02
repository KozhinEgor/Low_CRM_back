package com.crm.low_crm.model.dto;

import com.crm.low_crm.model.enumerate.TypeFilter;
import lombok.*;

import java.io.Serializable;
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FilterReport implements Serializable {
    private TypeFilter typeFilter;
}
