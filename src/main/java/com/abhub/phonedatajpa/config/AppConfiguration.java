package com.abhub.phonedatajpa.config;

import java.util.Arrays;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories("com.abhub.phonedatajpa.repository")
@PropertySource("classpath:application-dev.yml")
public class AppConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

    @Autowired
    private Environment         env;

    @Bean
    public DataSource dataSource() {

        logger.debug("Configuring Datasource");
        logger.debug("dataSourceClassName : " + env.getProperty("dataSourceClassName"));
        if (env.getProperty("url") == null && env.getProperty("databaseName") == null) {
            logger.error("Your database connection pool configuration is incorrect! The application" + "cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(env.getProperty("dataSourceClassName"));
        if (env.getProperty("url") == null || "".equals(env.getProperty("url"))) {
            config.addDataSourceProperty("databaseName", env.getProperty("databaseName"));
            config.addDataSourceProperty("serverName", env.getProperty("serverName"));
        } else {
            config.addDataSourceProperty("url", env.getProperty("url"));
        }
        config.addDataSourceProperty("user", "postgres");
        config.addDataSourceProperty("password", env.getProperty("password"));

        return new HikariDataSource(config);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.abhub.phonedatajpa.domain");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
