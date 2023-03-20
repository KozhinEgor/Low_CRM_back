package com.crm.low_crm.model.mapper;

import com.crm.low_crm.model.dto.Report;
import com.crm.low_crm.model.enity.ParametrReport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportMapper {
   public List<Report> toListReport(List<ParametrReport> parametrReports){
        List<Report> reports = new ArrayList<>();
        for(ParametrReport parametrReport : parametrReports){
            reports.add(new Report(parametrReport.getId(),parametrReport.getName(), parametrReport.getNameEn()));
        }
        return reports;
    }
}
