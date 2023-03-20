package com.crm.low_crm.controller;

import com.crm.low_crm.model.dto.Report;
import com.crm.low_crm.model.dto.Values;
import com.crm.low_crm.model.enity.ParametrReport;
import com.crm.low_crm.service.GenerateReport;
import com.crm.low_crm.service.ParametrReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ParametrReportService parametrReportService;
    @Autowired
    private GenerateReport generateReport;

    @GetMapping("/{id}")
    private ParametrReport getReportById(@PathVariable("id") Long id){
        return parametrReportService.getParametrReportById(id);
    }

    @GetMapping("/findByName/{name_en}")
    private ParametrReport getReportByNameEn(@PathVariable("name_en") String name_en){
        return parametrReportService.getParametrReportByNameEn(name_en);
    }

    @GetMapping("/allReport")
    private List<Report> findListReport(){
        return parametrReportService.findListReport();
    }

    @PostMapping("/generateReport")
    private List<Values> generateReport(@RequestBody ParametrReport parametrReport){
        return generateReport.generateValues(parametrReport);
    }
}
