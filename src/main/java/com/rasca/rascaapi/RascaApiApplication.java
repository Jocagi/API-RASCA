package com.rasca.rascaapi;

import com.rasca.rascaapi.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RascaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RascaApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean <AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		//Pagina de actividades a Implementarse
		registrationBean.addUrlPatterns("/api/actividades/*");
		return registrationBean;
	}
}


