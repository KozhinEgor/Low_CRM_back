package com.crm.low_crm.service;

import com.crm.low_crm.model.dto.Report;
import com.crm.low_crm.model.enity.ParametrReport;
import com.crm.low_crm.model.mapper.ReportMapper;
import com.crm.low_crm.repository.ParametrReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ParametrReportService {

    private ParametrReportRepository parametrReportRepository;

    private ReportMapper reportMapper;

    public ParametrReport getParametrReportById(Long id){

        return parametrReportRepository.findTopById(id);
    }
    public ParametrReport getParametrReportByNameEn(String name_en){
            ParametrReport parametrReport = parametrReportRepository.findTopByNameEn(name_en);
            if(parametrReport != null){
                parametrReport.getFilter().forEach(fil -> fil.setSelectValue(fil.getValue()));
            }
        return parametrReportRepository.findTopByNameEn(name_en);
    }
    public List<Report> findListReport(){
       return reportMapper.toListReport(parametrReportRepository.findAll());

    }
}
