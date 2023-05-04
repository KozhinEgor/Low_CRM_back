package com.crm.low_crm.service;

import com.base.BaseServiceImpl;
import com.crm.low_crm.model.dto.FilterReport;
import com.crm.low_crm.model.dto.ReportDto;
import com.crm.low_crm.model.enity.Report;
import com.crm.low_crm.model.mapper.ReportMapper;
import com.crm.low_crm.repository.reports.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReportService extends BaseServiceImpl<Report, ReportDto, FilterReport> {
    private final ReportRepository repository;
    private final ReportMapper mapper;


    public ReportService(ReportRepository repository, ReportMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<ReportDto> findAllByFilter(FilterReport filterReport, Pageable pageable) {
        return repository.findAll( pageable).map(mapper::toDTO);
    }
}
