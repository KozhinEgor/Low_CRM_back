package com.crm.low_crm.model.dto;

import com.crm.low_crm.model.enumerate.Period;
import com.crm.low_crm.model.enumerate.TypeCall;
import lombok.*;
import org.apache.commons.httpclient.NameValuePair;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
public class HistoryFilter {
    private String cmd;
    private String token;
    //YYYYmmddTHHMMSSZ
    private String start;
    private String end;
    private Period period;
    private TypeCall typeCall;
    private String limit;

    public NameValuePair[] getNameValuePair(){
        List<NameValuePair> array = new ArrayList<>();
        if(cmd != null){
            array.add(new NameValuePair("cmd",cmd));
        }
        if(token != null){
            array.add(new NameValuePair("token", token));
        }
        if(start != null){
            array.add(new NameValuePair("start", start));
        }
        if(end != null){
            array.add(new NameValuePair("end", end));
        }
        if(period != null){
            array.add(new NameValuePair("period", period.name()));
        }
        if(typeCall != null){
            array.add(new NameValuePair("typeCall", typeCall.name()));
        }
        if(limit != null){
            array.add(new NameValuePair("limit", limit));
        }
        return array.toArray(new NameValuePair[0]);
    }
}
