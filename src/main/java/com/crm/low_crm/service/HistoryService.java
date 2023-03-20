package com.crm.low_crm.service;

import com.crm.low_crm.model.dto.HistoryDTO;
import com.crm.low_crm.model.dto.HistoryFilter;
import com.crm.low_crm.model.enity.HistoryCall;
import com.crm.low_crm.model.enumerate.Period;
import com.crm.low_crm.repository.HistoryFilterRepository;
import com.crm.low_crm.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class HistoryService {

    private ATC atc;


    private HistoryRepository historyRepository;


    private HistoryFilterRepository filterRepository;

//
    public List<HistoryCall> addHistory(Period period){
//        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
        log.info("Начал загрузку звонков: "+ LocalDateTime.now());
        List <HistoryCall> historyCalls= new ArrayList<>();
        for(HistoryDTO dto : atc.getHistory(period)){
            if(historyRepository.findFirstByUid(dto.getUid()) != null)
                continue;
            HistoryCall historyCall = new HistoryCall();
            historyCall.setUid(dto.getUid());
            historyCall.setType(dto.getType());
            historyCall.setClient(dto.getClient());
            historyCall.setAccount(dto.getAccount());
            historyCall.setVia(dto.getVia());
            historyCall.setStart(ZonedDateTime.parse(dto.getStart()).plusHours(3l));
            historyCall.setWait(dto.getWait());
            historyCall.setDuration(dto.getDuration());
            historyCall.setRecord(dto.getRecord());
            historyCall.setQualityControl(dto.getQualityControl());
            historyCalls.add(historyCall);
        }
        return historyRepository.saveAll(historyCalls);
    }
    public List<HistoryCall> addHistory(HistoryFilter filter){
//        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
        List <HistoryCall> historyCalls= new ArrayList<>();
        for(HistoryDTO dto : atc.getHistory(filter)){
            if(historyRepository.findFirstByUid(dto.getUid()) != null)
                continue;
            HistoryCall historyCall = new HistoryCall();
            historyCall.setUid(dto.getUid());
            historyCall.setType(dto.getType());
            historyCall.setClient(dto.getClient());
            historyCall.setAccount(dto.getAccount());
            historyCall.setVia(dto.getVia());
            historyCall.setStart(ZonedDateTime.parse(dto.getStart()));
            historyCall.setWait(dto.getWait());
            historyCall.setDuration(dto.getDuration());
            historyCall.setRecord(dto.getRecord());
            historyCall.setQualityControl(dto.getQualityControl());
            historyCalls.add(historyCall);
        }
        return historyRepository.saveAll(historyCalls);
    }
    public List<HistoryCall> allHistory(){
        return historyRepository.findAll();
    }

    public List<HistoryCall> findbyDate(HistoryFilter filter){
        return filterRepository.findByFilter(filter);
    }
}
