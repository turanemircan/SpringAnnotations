package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.DbRepository;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;

public class MailService implements MessageService {

    // private DbRepository repository = new DbRepository();
    // private FileRepository repository = new FileRepository();
    // Birden fazla class bir objeye bağımlıysa gidip tek tek değiştirmemiz gerekiyor.

    // private Repository repository = new DbRepository(); // sıkı bir bağımlılık

    private Repository repository;

    public MailService(Repository repository) { // MailService Repository bağımlıdır, kullanmak zorundadır. Ancak ben Repositorynin türünü kullanacağın zaman enjekte ediyorum.
        this.repository = repository;
    }

    public void sendMessage(Message message) {
        System.out.println("Mesajınız mail ile gönderiliyor. Mesaj: " + message.getBody());
    }

    @Override
    public void saveMessage(Message message) {

        // Data ile ilgili işlemler: repo
        // DbRepository repository = new DbRepository();
        repository.save(message);
    }
}
