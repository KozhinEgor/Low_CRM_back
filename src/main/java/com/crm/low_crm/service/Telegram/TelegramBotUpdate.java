package com.crm.low_crm.service.Telegram;

import com.crm.low_crm.model.enity.User;
import com.crm.low_crm.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Service
@Component
public class TelegramBotUpdate extends TelegramLongPollingBot {
    @Autowired
    private  UserService userService;

    @Value("${bot.name}")
    private String BOT_NAME;
    @Value("${bot.token}")
    private String BOT_TOKEN;




    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            User user = userService.findOneUserByChatId(update.getMessage().getChatId());
            String message = "";
            switch (update.getMessage().getText()){
                case "/start":
                    if(user == null){
                       user = userService.createUser(update.getMessage().getChatId(), update.getMessage().getFrom());
                       message = String.format("Привет %s, чтобы получать рассылку ты должен ввести пароль",user.getName());
                    }
                    else{
                        message = String.format("%s ты уже зарегистрирвован, ты не обманешь систему", user.getName());
                    }
                    break;
                case "KozhinFamily":
                    if(user != null){
                        userService.setActive(user);
                        message = "Теперь ты подписан на ежедневную рассылку";
                    }
                    break;
                default:
                    if(user != null && user.isActive()){
                        message = "Пиши только когда я попрошу. Спасибо)";
                    } else if (user != null && !user.isActive() && user.getAttempt() > 0) {
                        user.setAttempt(user.getAttempt()-1);
                        userService.saveUser(user);
                        message = "Это не правильный пароль!!\n Попыток осталось "+ user.getAttempt();
                    }
                    else if (user != null){
                        message = "Ты не получаешь рассылку.\n Ты ввел пароль 10 раз неправильно.\n Пожалуйста не засоряй чат.";
                    }
                    else {
                        message = "Начни наш диалог написав '/start'";
                    }
                    break;
            }
           sendMessage(message, update.getMessage().getChatId());
        }
    }

    public void sendMessage(String message, Long chatId) {
        if(!message.isEmpty()){
            try (SendMessageAutoClosebale sendMessage = new SendMessageAutoClosebale()) {
                sendMessage.sendMessage.setChatId(chatId);
                sendMessage.sendMessage.setText(message);
                execute(sendMessage.sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            log.info("Сообщение пустое для чата :"+chatId);
        }
    }

    static class SendMessageAutoClosebale implements AutoCloseable {
        SendMessage sendMessage;
        SendMessageAutoClosebale(){
            sendMessage = new SendMessage();
        }

        @Override
        public void close() throws Exception {
            sendMessage = null;
        }
    }


}