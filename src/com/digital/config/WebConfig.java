package com.digital.config;

import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Satyam Kumar
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.digital")
@EnableTransactionManagement
@EnableScheduling
@PropertySource({ "classpath:application.properties", "classpath:sql.properties" })
public class WebConfig implements WebMvcConfigurer, TransactionManagementConfigurer {

	public static final Integer SECONDS_IN_DAY = 86400;

	@Autowired
	private Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setIgnoreUnresolvablePlaceholders(true);
		configurer.setIgnoreResourceNotFound(true);
		return configurer;
	}

	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate jdbcTemplateObject(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = DataSourceBuilder.create().driverClassName(env.getProperty("jdbc.driverClassName"))
				.url(env.getProperty("jdbc.url")).username(env.getProperty("jdbc.username"))
				.password(env.getProperty("jdbc.password")).build();
		/*
		 * BasicDataSource dataSource = new BasicDataSource();
		 * dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		 * dataSource.setUrl(env.getProperty("jdbc.url"));
		 * dataSource.setUsername(env.getProperty("jdbc.username"));
		 * dataSource.setPassword(env.getProperty("jdbc.password"));
		 */
		return dataSource;
	}

	@Bean
	public RestTemplate digitalRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**");
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
				.setCachePeriod(SECONDS_IN_DAY);
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
				.setCachePeriod(SECONDS_IN_DAY);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setCaseSensitive(false);
		configurer.setPathMatcher(matcher);
	}

}