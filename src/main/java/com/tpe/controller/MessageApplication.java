package com.tpe.controller;

import com.tpe.domain.Message;
import com.tpe.repository.DbRepository;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.WhatsappService;

public class MessageApplication {
    public static void main(String[] args) {

        Message message = new Message();
        message.setBody("Spring is COMING:)");

        // Bu mesajı mail ile gönderelim
//        MailService mailService = new MailService();
//        mailService.sendMessage(message);
//
//        // Bu mesajı whatsapp ile gönderelim
//        WhatsappService whatsappService = new WhatsappService();
//        whatsappService.sendMessage(message);

        // Referansı interfaceden alalım
        // Mesajı kaydedelim
        Repository repository = new FileRepository();
        MessageService service = new WhatsappService(repository);
        service.sendMessage(message);
        service.saveMessage(message);

        MessageService service2 = new MailService(repository);
        service2.sendMessage(message);
        service2.saveMessage(message);

        // Objeler arasındaki bağımlılığı gevşetmek için
        //   1-ref olarak interface
        //   2-bağımlılığı doğrudan vermek yerine daha
        //   sonra parametreli constructor(veya setter metodu) ile verdik

        // Problem:
        // 1-objeleri biz oluşturuyoruz
        // 2-objelerin bağımlılıklarını biz manuel olarak enjekte etmek zorundayız

        // Çözüm:
        // Spring is COMING:)
        // IoC,DI
    }
}
