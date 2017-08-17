package com.bjhxqh;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@SpringBootApplication
@EnableWebMvc
@EnableTransactionManagement
@MapperScan(basePackages = "com.bjhxqh.mapper")
@ComponentScan({
				"com.bjhxqh.config",
				"com.bjhxqh.controller",
				"com.bjhxqh.service",
				"com.bjhxqh.model",
				"com.bjhxqh.common.security",
				"com.bjhxqh.common.activiti",
				"org.activiti.rest.diagram",
				"com.bjhxqh.common.util"
				})
public class Application {

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(Application.class,args);
	}
}