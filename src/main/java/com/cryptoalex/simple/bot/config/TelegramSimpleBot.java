package com.cryptoalex.simple.bot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Component
public class TelegramSimpleBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onRegister() {
        log.debug("TelegramSimpleBot has been registered ...");
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("TelegramSimpleBot onUpdateReceived: [{}]", update.toString());

        if (update.hasMessage()) {
            Message incomingMessage = update.getMessage();
            Long chatId = incomingMessage.getChatId();

            if(update.getMessage().hasText()) {
                String text = incomingMessage.getText();

                if (text.equalsIgnoreCase("/start")) {
                    sendMessage(chatId, "--Bot has been run--");
                } else {
                    String responseMessage = String.format("received: [%s]", text);
                    sendMessage(chatId, responseMessage);
                }
            }
        }
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage newMessage = new SendMessage();
        newMessage.setChatId(chatId);
        newMessage.setText(message);

        try {
            execute(newMessage);
        } catch (TelegramApiException e) {
            log.error("Cannot send message!", e);
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
