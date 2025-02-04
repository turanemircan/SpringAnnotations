package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.Scanner;

@Configuration // Bu classta yapılandırma ayarları verilecek
@ComponentScan("com.tpe") // Bu pathde yer alan tüm componentların tarar
// default path:AppConfiguration classının içinde bulunduğu path tanımlıdır.
public class AppConfiguration {

    @Bean // thirdParty classtan bean oluşturulmasını sağlar. Sadece AppConfiguration class içinde kullanabilirsin.
    public Random random() {
        return new Random();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
