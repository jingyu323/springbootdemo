package com.rain.springbootstudy.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

@Component
public class TimeListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("监听器   销毁...");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("监听器初始化...");
	}

}
