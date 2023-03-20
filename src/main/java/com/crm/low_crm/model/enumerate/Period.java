package com.crm.low_crm.model.enumerate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Period {
    today("today"), // – звонки за сегодня
    yesterday("yesterday"), // – звонки за вчера
    this_week("this week"), // – звонки за текущую неделю
    last_week("last week"), // – звонки за прошедшую неделю
    this_month("this month"), // – звонки за текущий месяц
    last_month("last month"); // – звонки за прошедший месяц
    private String code;

    private Period(String code) {
        this.code=code;
    }

    @JsonCreator
    public static Period decode(final String code) {
        return Stream.of(Period.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
