package com.crm.low_crm.service;


import com.crm.low_crm.model.dto.FromTable;
import com.crm.low_crm.model.dto.GroupReport;
import com.crm.low_crm.model.dto.ShowValues;
import com.crm.low_crm.model.dto.Values;
import com.crm.low_crm.model.enity.ParametrReport;
import com.crm.low_crm.repository.ReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GenerateReport {


    private ReportRepository repository;

    public List<Values> generateValues(ParametrReport parametrReport){
        List<Values> values = new ArrayList<>();
        String from = "";
        for(FromTable table : parametrReport.getFrom()){
            if(table.getMain()){
                from = table.getName() + " " + from;
            }
            else{
                from = from + table.getJoinType() + " join " + table.getJoinTable() + " on " +
                         table.getColumnParent() + " = " + table.getColumnChild();
            }
        }
        String group = "";
        for(GroupReport groupReport : parametrReport.getGroup()){
            if(groupReport.getSelect()){
                if(groupReport.getFunc() != null){
                    group = group + groupReport.getFunc().name() + "(" + groupReport.getName() + ")";
                }
                else {
                    group = group + groupReport.getName();
                }
                break;
            }
        }
        String order = null;
        String columns = "component.name";
        for(ShowValues showValues : parametrReport.getShowValues()){
            if(!showValues.getDisabled()){
                if(showValues.getFunc() != null){
                    columns = columns + showValues.getFunc().name() + "(" + showValues.getName() + ") as value";
                }
                else {
                    columns = columns + showValues.getName() + " as value";
                }
                columns = columns + ", " + group + " as name ";
                repository.test(columns, from, order, group);
            }
        }

        return values;
    }
}
