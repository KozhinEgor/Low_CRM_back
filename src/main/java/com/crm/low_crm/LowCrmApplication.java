package com.crm.low_crm;

import com.crm.low_crm.service.Telegram.TelegramBotUpdate;
import com.crm.low_crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
@EnableScheduling
public class LowCrmApplication {
	public static void main(String[] args){

		SpringApplication.run(LowCrmApplication.class, args);
//		try {
//			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//			botsApi.registerBot();
//		} catch (TelegramApiException e) {
//			e.printStackTrace();
//		}
	}

}
