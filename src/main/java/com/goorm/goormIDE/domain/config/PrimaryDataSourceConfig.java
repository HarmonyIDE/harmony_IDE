//package com.goorm.goormIDE.domain.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.example.muk_table.domain.primary", // repository가 있는 패키지(폴더) 경로
//        entityManagerFactoryRef = "primaryEntityManagerFactory", // EntityManager의 이름
//        transactionManagerRef = "primaryTransactionManager" // 트랜잭션 매니저의 이름
//)
//public class PrimaryDataSourceConfig {
//
//
//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource") // application.yml에 작성된 DB와 관련된 설정 값들의 접두사
//    public DataSourceProperties primaryDatasourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource.hikari") // DB와 관련된 설정값들의 접두사에 .configuration을 붙여준다.
//    public DataSource primaryDataSource() {
//        return primaryDatasourceProperties()
//                .initializeDataSourceBuilder()
//                .type(HikariDataSource.class)
//                .build();
//    }
//
//
//    @Bean(name = "primaryEntityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder factoryBuilder) {
//        DataSource dataSource = primaryDataSource();
//        return factoryBuilder
//                .dataSource(dataSource)
//                .packages("com.example.muk_table.domain.primary") // repository와 관련된 엔티티들이 있는 패키지(폴더) 경로
//                .persistenceUnit("primaryEntityManager")
//                .build();
//    }
//
//    @Bean(name = "primaryTransactionManager")
//    @Primary
//    public PlatformTransactionManager primaryTransactionManager(
//            final @Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
//    ) {
//        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
//    }
//
//}
