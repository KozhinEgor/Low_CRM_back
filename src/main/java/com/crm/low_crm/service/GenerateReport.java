package com.crm.low_crm.service;


import com.crm.low_crm.model.dto.*;
import com.crm.low_crm.model.enity.BaseReport;
import com.crm.low_crm.model.enity.ParametrReport;
import com.crm.low_crm.model.enity.Report;
import com.crm.low_crm.repository.ReportTelegramRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class GenerateReport {

    public UUID generateValues(FilterReport parametrReport){
        BaseReport report = null;
        switch (parametrReport.getTypeFilter()){
            case(TypeFi)
        }
    }
}
