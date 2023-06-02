package com.crm.low_crm.controller;

import com.base.BaseController;
import com.base.BaseService;
import com.crm.low_crm.model.dto.FilterReport;
import com.crm.low_crm.model.dto.ReportDto;
import com.crm.low_crm.model.dto.Values;
import com.crm.low_crm.model.enity.ParametrReport;
import com.crm.low_crm.model.enity.Report;
import com.crm.low_crm.service.GenerateReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController extends BaseController<Report, ReportDto, FilterReport> {


    @Autowired
    private GenerateReport generateReport;

    public ReportController(BaseService<Report, ReportDto, FilterReport> service) {
        super(service);
    }

    @PostMapping("/generateReport")
    private List<Values> generateReport(@RequestBody FilterReport filterReport){
        return generateReport.generateValues(filterReport);
    }
}
