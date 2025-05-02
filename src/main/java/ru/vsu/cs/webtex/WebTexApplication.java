package ru.vsu.cs.webtex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.vsu.cs.webtex.service.MongoToPostgresMigrator;

@SpringBootApplication(scanBasePackages = "ru.vsu.cs.webtex")
public class WebTexApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTexApplication.class, args);
    }
//    @Bean
//    CommandLineRunner runner(MongoToPostgresMigrator migrator) {
//        return args -> migrator.migrateData();
//    }

}
