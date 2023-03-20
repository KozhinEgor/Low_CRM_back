package com.crm.low_crm.model.enity;

import com.crm.low_crm.model.enumerate.TypeCall;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;


@Getter
@Setter
@Entity
@Table(name = "history_call", schema = "public")
public class HistoryCall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*уникальный идентификатор звонка*/
    @Column(unique = true)
    private String uid;
    /*тип вызова: in / out / missed*/
    private TypeCall type;
    /*номер клиента*/
    private String client;
    /*логин сотрудника, который разговаривал с клиентом или имя группы или код: ivr / fax, если звонок не дошел до сотрудника*/
    private String account;
    /*номер телефона, через который пришел входящий звонок или АОН для исходящего вызова*/
    private String via;
    /*время начала звонка в UTC*/
    private ZonedDateTime start;
    /*время ожидания на линии (секунд)*/
    private Integer wait;
    /*длительность разговора (секунд)*/
    private Integer duration;
    /*ссылка на запись разговора*/
    private String record;
    /*оценка качества разговора, если есть*/
    private String QualityControl;
}
