package com.cryptoalex.simple.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {
//        "com.telegram.secretary.bot",
//        "org.telegram.telegrambots"
//})
public class TelegramSimpleBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramSimpleBotApplication.class, args);
    }

}
