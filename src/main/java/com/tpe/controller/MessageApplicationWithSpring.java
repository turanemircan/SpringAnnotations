package com.tpe.controller;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.repository.DbRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MessageService;
import com.tpe.service.SlackService;
import com.tpe.service.SmsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public class MessageApplicationWithSpring {
    public static void main(String[] args) {

        Message message = new Message();
        message.setBody("Welcome SPRING:)");

        // config classını okur ve componentscan ile componentları ve beanleri tarar ve her birinden
        // sadece 1 tane Spring bean oluşturur ve contexte atar ve hazır olarak bekletir.
        // bean istendiğinde gerekliyse içine bağımlılığını da enjekte ederek gönderir.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        // Contenier'dan bean isteyebilmemiz için contex objesine ihtiyacımız var.

        // Mesajı sms ile gönderelim: SmsService'in objesi gerekli
        MessageService service01 = context.getBean(SmsService.class); // new'lemedik, rica ettik :)
        service01.sendMessage(message);

        MessageService service02 = context.getBean("sms_service", MessageService.class); // MessageService bir interface olduğu için bean oluşturma işlemini ona implement olan bir class yapar.
        // Hangi class'tan bean oluşturulacağını biz belirleyebiliriz. Bunun için o class'tan oluşturulan bean'i string olarak yazarız.
        service02.sendMessage(message);

        // Mesajı Slack ile gönderelim

        MessageService service03 = context.getBean(SlackService.class);
        service03.sendMessage(message);

        // Bağımlılık gerekirse
        MessageService service04 = context.getBean(SmsService.class);
        service04.sendMessage(message);
        service04.saveMessage(message);
        // smsService i newlemedik
        // service repoya bağımlı ama biz enjekte etmedik
        // repo objesini de biz oluşturmadık
        // Spring SAĞOLSUN:)

        Repository repository = context.getBean(DbRepository.class);
        repository.save(message);

        // Random bir değer üretelim ve yazdıralım
        // Random random = new Random();
        Random random = context.getBean(Random.class);
        System.out.println("Random değer: " + random.nextInt(100));

        MessageService service05 = context.getBean(SlackService.class);
        // service05.saveMessage(message);

        // Context objemiz varsa beani getBean ile isteyip kullanabiliriz
        // Diğer classlarda ise enjekte ederek aynı beani kullanabiliriz

        MessageService service06 = context.getBean(SlackService.class);
        MessageService service07 = context.getBean(SlackService.class);

        if (service06 == service07) {
            System.out.println("Aynı Objeler.");
            System.out.println(service06);
            System.out.println(service07);
        } else {
            System.out.println("Farklı Objeler.");
            System.out.println(service06);
            System.out.println(service07);
        }

        // prototype olan beani sonlandırmak için
        context.getBeanFactory().destroyBean(service06);

        SlackService service08 = context.getBean(SlackService.class);
        service08.printContact();
        System.out.println("---------------------------------------");
        service08.getContact();

        context.close();

        // Contex'i kapatıktan sonra beanlere ulaşamayız.
//        SmsService service = context.getBean(SmsService.class);
//        service.sendMessage(message);
    }
}

/*
Singleton Scope
Spring konteyneri, bir bean tanımı için yalnızca bir örnek (instance) oluşturur ve bu örneği tüm uygulama boyunca paylaşır.

Bean, Spring konteyneri başlatıldığında oluşturulur.
Uygulama kapanana kadar aynı örnek kullanılır.


Stateless (Durumsuz) nesneler için kullanılır.
Aynı davranışı ve veriyi paylaşması gereken hizmet sınıfları (örneğin, Service ve Repository sınıfları) için idealdir.
Tek bir örnek oluşturulduğu için bellek tüketimini azaltır.
----------------
Prototype Scope
Spring konteyneri, bir bean'e her erişimde yeni bir örnek (instance) oluşturur.

Bean, Spring konteyneri tarafından her çağrıldığında (örneğin, getBean() metodu ile) yeniden oluşturulur.
Kısa ömürlüdür ve Spring konteyneri yaşam döngüsünü yönetmez.

Stateful (Durum bilgisi taşıyan) nesneler için kullanılır.
Her istekte farklı veriyle çalışması gereken nesneler (örneğin, kullanıcı oturum bilgisi veya dosya işlemleri) için uygundur.

-------------
Singleton: Varsayılan olduğu için Spring genellikle bu scope'u kullanır ve çoğu servis veya repository sınıfı için yeterlidir.
Prototype: Kısa ömürlü nesneler için kullanışlıdır, ancak manuel olarak yönetilmesi gerekebilir. Özellikle Spring konteynerinin dışında kullanılan prototip nesnelerin yaşam döngüsüne dikkat edilmelidir.
*/