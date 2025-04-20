package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.TaskRepository;

@Configuration
public class LoadDatabase {
   
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            // log.info("preloading "+ repository.save(new Task("Create new project", false)));
            // log.info("preloading "+ repository.save(new Task("Create new task", false)));
        };
    }
}
