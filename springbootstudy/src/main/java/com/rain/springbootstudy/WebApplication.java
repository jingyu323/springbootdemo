package com.rain.springbootstudy;

import java.util.EnumSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.rain.springbootstudy.filter.TimeFilter;
import com.rain.springbootstudy.servlet.TimeServlet;

@SpringBootApplication
@MapperScan("com.rain.springbootstudy.domain")

public class WebApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	/***
	 * 可以在启动的 时候添加相关的 配置信息的加载  filter ,lisener ,intercepter 等
	 */
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		super.onStartup(servletContext);
		
		

		// 配置 Servlet
		// servletContext.addServlet("servletTest",new TimeServlet())
		// .addMapping("/servletTest");
		// // 配置过滤器
		// servletContext.addFilter("timeFilter",new TimeFilter())
		// .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
		// 配置监听器
		// servletContext.addListener(new ListenerTest());

	}
}
