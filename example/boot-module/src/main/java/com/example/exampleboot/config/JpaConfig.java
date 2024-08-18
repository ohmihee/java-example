package com.example.exampleboot.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

public class JpaConfig {
    private String temp;
}

/**
 * 아래와 같은 설정은 spring이 데이터 소스, 엔티티 매니터 팩토리, 트랜잭션 매니저 등을 자동으로 설정하고 관리할 수 있게 해준다.
 * 위의 설정으로 개발자는 서비스 구현 시에 코드의 복잡성을 줄일 수 있다.
 * 스프링 부트를 사용 시에는 아래와 같은 설정을 application.yml을 통해 더욱 간단히 설정하는 것이 가능
 * */
//@Configuration
//@EnableJpaRepositories
//@EnableTransactionManagement // -> 선언적 트랜잭션 관리를 활성화하는 어노테이션
//public class JpaConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        // 별도의 설정 없이 내장형 HSQL 데이터베이스 설정
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        return builder.setType(EmbeddedDatabaseType.HSQL).build();
//    }
//
//    /**
//     * JPA 엔티티 매니터 팩토리 구성, JPA 엔티티 매니저를 생성하여 준다.
//     * -> 엔티티 클래스의 패키지를 자동으로 스캔하고, 엔티티 매니저가 이를 관리할 수 있게 하여 주는 설정
//     * */
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.example");
//        factory.setDataSource(dataSource());
//        return factory;
//    }
//
//    /**
//     * JPA 트랜잭션 매니저 생성, 엔티티 매니터 팩토리를 통해 트랜잭션을 관리
//     * -> @Transactional 어노테이션을 사용하여 메서드 또는 클래스 단위로 트랜잭션을 관리할 수 있도록 도와주는 설정
//     * */
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }
//
//}
