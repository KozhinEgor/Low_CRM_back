package com.crm.low_crm.configuration;

import com.crm.low_crm.model.enity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource({ "classpath:application.yml" })
@EnableJpaRepositories(
        basePackages = "com.crm.low_crm.repository",
        entityManagerFactoryRef = "lowCrmEntityManagerFactory",
        transactionManagerRef = "lowCrmTransactionManager"
)
@EnableTransactionManagement
public class LowCrmPersistenceConfiguration {



    @Value("${spring.jpa.properties.hibernate.show_sql:false}")
    private String showSql;


    @Bean("lowCrmEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean lowCrmEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("lowCrmDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(User.class)
                .persistenceUnit("user")
                .properties(additionalJpaProperties())
                .build();
    }

    private Map<String, ?> additionalJpaProperties() {
        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.show_sql", showSql);

        return props;
    }

    @Bean("lowCrmDataSourceProps")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties lowCrmDataSourceProps() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean("lowCrmDataSource")
    public DataSource lowCrmDataSource(@Qualifier("lowCrmDataSourceProps") DataSourceProperties props) {
        return props.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean("lowCrmTransactionManager")
    public PlatformTransactionManager lowCrmTransactionManager(
            @Qualifier("lowCrmEntityManagerFactory") LocalContainerEntityManagerFactoryBean emFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emFactory.getObject());
        return transactionManager;
    }
}
