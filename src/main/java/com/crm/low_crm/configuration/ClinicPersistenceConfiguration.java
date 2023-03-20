package com.crm.low_crm.configuration;


import com.clinic.entity.Clients;
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
        basePackages = "com.clinic.repository",
        entityManagerFactoryRef = "clinicEntityManagerFactory",
        transactionManagerRef = "clinicTransactionManager"
)
@EnableTransactionManagement
public class ClinicPersistenceConfiguration {

    @Value("${clinic.jpa.show-sql:false}")
    private String showSql;
    @Value("${clinic.jpa.properties.hibernate.dialect:null}")
    private String dialect;


    @Bean("clinicEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean clinicEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("clinicDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(Clients.class)
                .persistenceUnit("clients")
                .properties(additionalJpaProperties())
                .build();
    }

    private Map<String, ?> additionalJpaProperties() {
        HashMap<String, Object> props = new HashMap<>();
        if (dialect != null) {
            props.put("hibernate.dialect", dialect);
        }
        props.put("hibernate.show_sql", showSql);
        return props;
    }

    @Bean("clinicDataSourceProps")
    @ConfigurationProperties(prefix = "clinic.datasource")
    public DataSourceProperties clinicDataSourceProps() {
        return new DataSourceProperties();
    }


    @Bean("clinicDataSource")
    public DataSource clinicDataSource(@Qualifier("clinicDataSourceProps") DataSourceProperties props) {
        return props.initializeDataSourceBuilder().build();
    }


    @Bean("clinicTransactionManager")
    public PlatformTransactionManager clinicTransactionManager(
            @Qualifier("clinicEntityManagerFactory") LocalContainerEntityManagerFactoryBean emFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emFactory.getObject());
        return transactionManager;
    }
}
