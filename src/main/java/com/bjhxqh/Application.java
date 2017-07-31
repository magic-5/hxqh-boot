package com.bjhxqh;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableTransactionManagement
@MapperScan(basePackages = "com.bjhxqh.mapper")
public class Application {

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(Application.class,args);
	}
}