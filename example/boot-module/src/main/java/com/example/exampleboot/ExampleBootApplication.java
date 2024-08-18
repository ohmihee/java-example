package com.example.exampleboot;

import com.example.exampleboot.config.SecurityConfig;
import com.example.domain.user.User;
import com.example.domain.user.store.storeImpl.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.NoSuchElementException;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.example"})
@EntityScan(basePackages = {"com.example"})
@ComponentScan(basePackages = {"com.example"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {SecurityConfig.class})}
)
// https://stackoverflow.com/questions/53379113/springboot-how-to-exclude-configuration-class-in-dependency-dependency
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

    @Bean
    CommandLineRunner runner(UserRepository repository) {
        return args -> {
            User user = new User();
            user.setNickName("miheeeee");
            repository.save(user);
            User saved = repository.findById(user.getId()).orElseThrow(NoSuchElementException::new);
            System.out.println("saved user id"+saved.getId());
            // 엔티티는 영속성 컨텍스트에 의해서 관리된다. user의 id 값을 따로 설정해주시 않았지만, 영속성 컨텍스트에 의해 user를 새롭게 저장 후에 id를 담고 있는다.
        };

    }



}
