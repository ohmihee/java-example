package com.example.exampleboot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.example"})
@EntityScan(basePackages = {"com.example"})
@ComponentScan(basePackages = {"com.example"})
public class ExampleBootApplication {
    private final TestBean testBean;

    @Autowired
    public ExampleBootApplication(TestBean testBean) {
        this.testBean = testBean;
    }
    @PostConstruct
    public void dependencyTest() {
        testBean.dependencyTest();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleBootApplication.class, args);
    }



}
