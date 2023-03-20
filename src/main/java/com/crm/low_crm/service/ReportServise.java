package com.crm.low_crm.service;

import com.crm.low_crm.model.enity.HistoryCall;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReportServise {



    public String messageDayReport(List<HistoryCall> calls){
        String message = "За вчера было " + calls.size()  + " " + wordByNumber("звон","ок", "ка", "ков", calls.size());
        return message;
    }

    public String wordByNumber(String wordStart, String  wordEnd1,String  wordEnd2_4, String  wordEnd5_20,Integer number) {
        if(number == null) {return wordStart + wordEnd5_20;}
        int rem = number% 100;

        if(rem > 0 && (rem<5 || rem>20)){
            rem = rem%10;
            if(rem == 1) return wordStart + wordEnd1;
            else if (rem == 0) return wordStart +wordEnd5_20;
            else return wordStart + wordEnd2_4;
        }
        else return wordStart + wordEnd5_20;

    }
}
