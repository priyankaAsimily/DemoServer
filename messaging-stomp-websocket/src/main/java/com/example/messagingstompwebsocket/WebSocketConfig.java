package com.example.messagingstompwebsocket;

import java.util.logging.SocketHandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue");
		config.setApplicationDestinationPrefixes("/app");
		
	}
	

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket")
		.setAllowedOrigins("http://localhost:8080")
		.withSockJS();
//		   registry.setPreservePublishOrder(true);
	}

	   @Override
	    public void configureClientOutboundChannel(ChannelRegistration registration) {
	        registration.taskExecutor().corePoolSize(1);
	    }

	    @Override
	    public void configureClientInboundChannel(ChannelRegistration registration) {
	        registration.taskExecutor().corePoolSize(1).queueCapacity(1);
	    }
}
