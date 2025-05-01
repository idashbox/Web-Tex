package ru.vsu.cs.webtex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.vsu.cs.webtex")
public class WebTexApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTexApplication.class, args);
    }

}
