package com.rain.springbootstudy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.rain.springbootstudy.filter.TimeFilter;
import com.rain.springbootstudy.intercepter.TimeInterceptor;
import com.rain.springbootstudy.listener.TimeListener;

/**
 * implements WebMvcConfigurer 才能添加拦截器 还可以 继承 WebMvcConfigurationSupport 这个类
 * 
 * @author Rain
 *
 */

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private TimeInterceptor timeInterceptor;

	/**
	 * 添加时间过滤器
	 * 
	 * @return
	 */

	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		// 添加时间过滤器
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);

		return registrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean(new TimeListener());
	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}


}
