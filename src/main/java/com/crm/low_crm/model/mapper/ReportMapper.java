package com.crm.low_crm.model.mapper;

import com.base.BaseMapper;
import com.crm.low_crm.model.dto.ReportDto;
import com.crm.low_crm.model.enity.BaseReport;
import com.crm.low_crm.model.enity.ParametrReport;
import com.crm.low_crm.model.enity.Report;
import org.mapstruct.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ReportMapper extends BaseMapper<Report, ReportDto> {

    ReportDto toDTO(Report entity);

    @Mapping(target = "id", ignore = true)
    Report toEntity(ReportDto dto);

    @Mapping(target = "id", ignore = true)
    void mergeToExistingEntity(@MappingTarget Report entity, ReportDto dto);

}
