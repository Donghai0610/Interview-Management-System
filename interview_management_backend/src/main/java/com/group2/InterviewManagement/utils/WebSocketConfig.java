package com.group2.InterviewManagement.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final UserUpdaterHandler userUpdaterHandler;

    public WebSocketConfig(UserUpdaterHandler userUpdaterHandler) {
        this.userUpdaterHandler = userUpdaterHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(userUpdaterHandler, "/user-update")
                .setAllowedOrigins("*");
    }

//    @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer() {
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(8192);
//        container.setMaxBinaryMessageBufferSize(8192);
//        container.setMaxSessionIdleTimeout(60000L); // Set idle timeout to 60 seconds
//        return container;
//    }
}

