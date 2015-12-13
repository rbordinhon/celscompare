package rbprojects.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.dialect.H2Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfigs implements ApplicationContextAware {

	private Logger logger = LoggerFactory.getLogger(PersistenceConfigs.class);
	private ApplicationContext context;

	
	@Bean(name="celsDataSource")
	public DataSource dataSource(){
		org.apache.commons.dbcp.BasicDataSource dataSource = new BasicDataSource();
		if(isTesteEnvironment()){
			dataSource.setDriverClassName("org.h2.Driver");
			final String url = "jdbc:h2:./data/test";//;INIT=RUNSCRIPT FROM 'classpath:/teste.sql'";
			dataSource.setUrl(url);
			dataSource.setUsername("sa");
			dataSource.setPassword("");
			dataSource.setInitialSize(5);
			dataSource.setMaxActive(10);
		
		}else {
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/celscompare");
			dataSource.setUsername("adminz9kFsCv");
			dataSource.setPassword("l6VXbFh_z8DG");
			dataSource.setInitialSize(5);
			dataSource.setMaxActive(10);
		}
		return dataSource;
		
	}

	private boolean isTesteEnvironment() {
		return System.getProperties().containsKey("ambiente") && System.getProperty("ambiente").equalsIgnoreCase("Teste");
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(context.getBean("celsDataSource",DataSource.class));
		em.setPackagesToScan(new String[] { "rbprojects.domain" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		final LocalContainerEntityManagerFactoryBean local = context.getBean(LocalContainerEntityManagerFactoryBean.class);
		if(isTesteEnvironment()){
			DataSource source = context.getBean("celsDataSource",DataSource.class);
			try {
				initializeTesteSql(source);
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
				throw e;
			}
		}
		transactionManager.setEntityManagerFactory(local.getObject());
		return transactionManager;
	}

	private void initializeTesteSql(DataSource source) throws SQLException{
		Connection conn= null;
		PreparedStatement ps = null;
		try {
			conn = source.getConnection();
			ps = conn.prepareStatement("RUNSCRIPT FROM 'classpath:/teste.sql'");
			ps.execute();
		}
		finally {
			try {
				if(ps != null ) ps.close();
				
			} catch (Exception e) {
			}
			try {
				if(conn != null ) conn.close();
			} catch (Exception e) {
			}
		}
		
	}
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.context = arg0;

	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		if(isTesteEnvironment()){
			properties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		}else {
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		}
		
		return properties;
	}

}
