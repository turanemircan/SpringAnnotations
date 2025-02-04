package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Random;
import java.util.Scanner;

@Component("slack_service") // Class'ta oluşturulacak bean default ismi class isminin baş harfi küçültülüp konulur.
// Ancak biz default isim yerine kendimizde adlandırabiliriz. Bunun için @Componenet() içerisine String değer girilir.
@Scope("prototype")
public class SlackService implements MessageService {

    @Autowired
    private Random random;

    @Autowired
    private Scanner scanner;

    @Override
    public void sendMessage(Message message) {
        System.out.println("Mesajınız Slack ile gönderiliyor. Mesaj: " + message.getBody());
    }

    @Override
    public void saveMessage(Message message) {
        // Random bir değer üretelim
        // Random random = new Random();
        System.out.println("Random değer: " + random.nextInt(100));
        System.out.print("Spring Nasıl? ");
        System.out.println(scanner.nextLine());
    }

    //-----------------------------------------------------------------------------------------------------

    @PostConstruct
    public void postConstruct() {
        System.out.println("--------slack service objesi üretildi.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("--------slack service objesi imha edildi.");
    }
}
