package com.rain.springbootstudy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.rain.springbootstudy.filter.TimeFilter;
import com.rain.springbootstudy.intercepter.TimeInterceptor;
import com.rain.springbootstudy.listener.TimeListener;
import com.rain.springbootstudy.servlet.TimeServlet;

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
//
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		// 中文乱码解决方案
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);// 设定json格式且编码为UTF-8
		fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);

		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastJsonHttpMessageConverter);

		// return new HttpMessageConverters(converter);

	}

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

	/**
	 * 添加监听器
	 * 
	 * @return
	 */
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

	/**
	 * 注册servlet
	 */

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new TimeServlet(), "/servletTest");
	}

	/**
	 * 注册fastjson ,用于替换自带的json解 配置了不起作用 不知道为啥 ，以后在解决
	 * 
	 * @return
	 */

//	@Bean
//	public HttpMessageConverters fastJsonHttpMessageConverters() {
//		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//
	// // 中文乱码解决方案
//		List<MediaType> mediaTypes = new ArrayList<>();
	// mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);// 设定json格式且编码为UTF-8
//		fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
//
//		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//
//		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
//
//		return new HttpMessageConverters(converter);
//
//	}

	/**
	 * 重写 允许跨域的方法
	 */

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/fastjson/**").allowedOrigins(
				"http://localhost:8088");// 允许 8088 端口访问
	}


}
