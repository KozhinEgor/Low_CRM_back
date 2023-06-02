package com.crm.low_crm.controller;
import com.crm.low_crm.model.dto.HistoryFilter;
import com.crm.low_crm.model.enity.HistoryCall;
import com.crm.low_crm.model.enumerate.Period;
import com.crm.low_crm.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ApiController {


    @Autowired
    private HistoryService serviceH;

    @GetMapping("/addToday")
    private List<HistoryCall> addToday(){ return serviceH.addHistory(Period.today);}

    @GetMapping("/getAll")
    private List<HistoryCall> getAllHistory(){
        return serviceH.allHistory();
    }

    @PostMapping("/addPeriod")
    private List<HistoryCall> addPeriod(@RequestBody String period){
        log.info(period);
        return serviceH.addHistory(Period.decode(period));
    }

    @PostMapping("/findByDate")
    private List<HistoryCall> findByDate(@RequestBody HistoryFilter filter){
        return serviceH.findbyDate(filter);
    }

    @PostMapping("/addByFilter")
    private String addStartAndEnd(@RequestBody HistoryFilter historyFilter){
        serviceH.addHistory(historyFilter);
        return "ok";
    }
    @GetMapping("/test")
    private String test(){
        return "ок";
    }
}
