package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

@Configuration // Bu classta yapılandırma ayarları verilecek
@ComponentScan("com.tpe") // Bu pathde yer alan tüm componentların tarar
// default path:AppConfiguration classının içinde bulunduğu path tanımlıdır.
@PropertySource("classpath:application.properties") // application.properties dosyasındaki bilgilerin okunmasını sağlar
public class AppConfiguration {

    // Springe ait bir interface, bean oluşturmadan enjekte edilebilir,
    // PropertySourcedaki dosyanın içindeki özelliklere(propertylere)
    // ve hatta uygulamanın çalıştığı ortam değişkenlerine
    // erişmemizi sağlayan metodlar içerir.

    @Autowired
    private Environment environment; // Environment özel olarak Spring kendisi bir Bean üretir bizim üretmemize gerek yok.

    @Bean // thirdParty classtan bean oluşturulmasını sağlar. Sadece AppConfiguration class içinde kullanabilirsin.
    public Random random() {
        return new Random();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    // value anatasyonu ile yaptığımız işlemi Environment ve Properties Class'ı ile de yapabiliriz.

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        // properties.put("mymail", "techproed@gmail.com");
        properties.put("mymail", environment.getProperty("eposta"));
        properties.put("myphone", environment.getProperty("phone"));
        properties.put("password", environment.getProperty("password.admin"));
        return properties;
    }
}
