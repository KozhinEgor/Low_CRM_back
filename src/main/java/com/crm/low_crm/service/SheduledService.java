package com.crm.low_crm.service;

import com.crm.low_crm.model.dto.HistoryFilter;
import com.crm.low_crm.model.enumerate.Period;
import com.crm.low_crm.service.Telegram.TelegramBotUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Slf4j
@AllArgsConstructor
public class SheduledService {

    private HistoryService service;


    private UserService userService;


    private ReportServise reportServise;

    private final TelegramBotUpdate telegram;


    @Scheduled(cron = "0 0 4 * * *")
    public void getHistory(){
        log.info("Старт еженедельного сбора данных");
        service.addHistory(Period.yesterday);
        HistoryFilter filter = new HistoryFilter();
        filter.setPeriod(Period.yesterday);
       String message =  reportServise.messageDayReport(service.findbyDate(filter));
        for(Long chat : userService.findAllChatsActiveUsers()){
            telegram.sendMessage(message, chat);
        }
    }
}
