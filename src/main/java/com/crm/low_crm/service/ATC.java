package com.crm.low_crm.service;

import com.crm.low_crm.model.dto.HistoryDTO;
import com.crm.low_crm.model.dto.HistoryFilter;
import com.crm.low_crm.model.enumerate.Period;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ATC {
    @Value("$token")
    String token;
    HttpClient client = new HttpClient();
    String uri = "https://vats401691.megapbx.ru/sys/crm_api.wcgp";
    public List<HistoryDTO> getHistory(Period period){
         HistoryFilter filter = new HistoryFilter();
        filter.setCmd("history");
        filter.setToken("72797338-2d60-46c1-b8a1-405b111c18bb");
        filter.setPeriod(period);
        try{
            return new CsvToBeanBuilder<HistoryDTO>(Objects.requireNonNull(postATC(filter))).withType(HistoryDTO.class).build().parse();
        }
        catch (Exception e){
            log.error("Ошибка обращения к АТС");
            return null;
        }
    }
    public List<HistoryDTO> getHistory(HistoryFilter filter){
        filter.setCmd("history");
        filter.setToken("72797338-2d60-46c1-b8a1-405b111c18bb");
        try{
            return new CsvToBeanBuilder<HistoryDTO>(Objects.requireNonNull(postATC(filter))).withType(HistoryDTO.class).build().parse();
        }
        catch (Exception e){
            log.error("Ошибка обращения к АТС");
            return null;
        }
    }
    private BufferedReader postATC(HistoryFilter filter) {
        String in = null;
        try {
            PostMethod method = new PostMethod(uri);
            method.setRequestBody(filter.getNameValuePair());
            //Add any parameter if u want to send it with Post req.
            int statusCode = client.executeMethod(method);
            if (statusCode != -1) {
                InputStream str = method.getResponseBodyAsStream();
                return new BufferedReader(new InputStreamReader(str, StandardCharsets.UTF_8));

            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
