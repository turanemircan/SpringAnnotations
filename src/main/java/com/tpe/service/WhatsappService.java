package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;

public class WhatsappService implements MessageService {

    // private FileRepository repository = new FileRepository();

    // private Repository repository = new DbRepository(); // sıkı bir bağımlılık

    private Repository repository;

    public WhatsappService(Repository repository) { // Bağımlılığı başlangıçta vermeyip daha sonra enjekte ediyoruz.
        this.repository = repository;
    }

    @Override
    public void sendMessage(Message message) {
        System.out.println("Mesajınız whatsapp ile gönderiliyor. Mesaj: " + message.getBody());
    }

    @Override
    public void saveMessage(Message message) {
        repository.save(message);
    }
}
