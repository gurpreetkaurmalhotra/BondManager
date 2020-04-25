package com.gurpreet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource (ignoreResourceNotFound = true, value = "classpath:${spring.profiles.active}/db.properties")
@EnableTransactionManagement
@EnableJpaRepositories (basePackages = { "com.gurpreet.repository" },
    entityManagerFactoryRef = "bondManagerEntityManager",
    transactionManagerRef = "bondManagerTransactionManager")
public class DbConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean bondManagerEntityManager() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(bondManagerDataSource());
        emf.setPackagesToScan("com.gurpreet");
        emf.setPersistenceUnitName("bondManagerEntityManager");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", environment.getProperty("spring.jpa.hibernate.dialect"));
        jpaProperties.put("hibernate.show-sql", environment.getProperty("spring.jpa.show-sql"));
        emf.setJpaPropertyMap(jpaProperties);
        return emf;
    }

    @Bean
    @Primary
    public DataSource bondManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean(name = "bondManagerTransactionManager")
    public PlatformTransactionManager bondManagerTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bondManagerEntityManager().getObject());
        return transactionManager;
    }

}
