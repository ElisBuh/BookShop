package util;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.senla")
@PropertySource(value = "classpath:hsqldb.properties")
@EnableTransactionManagement
public class HsqldbDataConfig {

    @Value("${database.driverClassName}")
    private String DB_Driver;

    @Value("${database.password}")
    private String DB_PASSWORD;

    @Value("${database.url}")
    private String DB_URL;

    @Value("${database.username}")
    private String DB_USERNAME;

    @Value("${database.hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${database.showSql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass(DB_Driver);
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUser(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        Properties hibernateProper = new Properties();
        hibernateProper.put("hibernate.dialect", HIBERNATE_DIALECT);
        hibernateProper.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        sessionFactoryBean.setHibernateProperties(hibernateProper);
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("BCryptPasswordEncoder()");
        return new BCryptPasswordEncoder();
    }
}
