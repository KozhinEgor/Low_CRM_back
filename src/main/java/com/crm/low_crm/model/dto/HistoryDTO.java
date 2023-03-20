package com.crm.low_crm.model.dto;

import com.crm.low_crm.model.enumerate.TypeCall;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;


@Data
public class HistoryDTO {
    /*уникальный идентификатор звонка*/
    @CsvBindByPosition(position = 0)
    private String uid;
    /*тип вызова: in / out / missed*/
    @CsvBindByPosition(position = 1)
    private TypeCall type;
    /*номер клиента*/
    @CsvBindByPosition(position = 2)
    private String client;
    /*логин сотрудника, который разговаривал с клиентом или имя группы или код: ivr / fax, если звонок не дошел до сотрудника*/
    @CsvBindByPosition(position = 3)
    private String account;
    /*номер телефона, через который пришел входящий звонок или АОН для исходящего вызова*/
    @CsvBindByPosition(position = 4)
    private String via;
    /*время начала звонка в UTC*/
    @CsvBindByPosition(position = 5)
    private String start;
    /*время ожидания на линии (секунд)*/
    @CsvBindByPosition(position = 6)
    private Integer wait;
    /*длительность разговора (секунд)*/
    @CsvBindByPosition(position = 7)
    private Integer duration;
    /*ссылка на запись разговора*/
    @CsvBindByPosition(position = 8)
    private String record;
    /*оценка качества разговора, если есть*/
    @CsvBindByPosition(position = 9)
    private String QualityControl;
}
