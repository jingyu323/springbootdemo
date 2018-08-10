package com.rain.springbootstudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.rain.springbootstudy.websocket.WebSocketServer;

/***
 * 
 * WebSocket 该方式适用于 jar 包方式运行和 war 方式运行。
 * 
 * @author Rain
 *
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketServer(), "/webSocketServer/*");
	}

	@Bean
	public WebSocketHandler webSocketServer() {
		return new WebSocketServer();
	}

}
