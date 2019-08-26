package com.boldare.websocketshowcase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new MyHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        DefaultHandshakeHandler handshakeHandler = new DefaultHandshakeHandler();
        handshakeHandler.setSupportedProtocols("ocpp1.6");

        registry.addHandler(myWebSocketHandler(), "/ocpp/{chargerName}")
                .setAllowedOrigins("*")
                .setHandshakeHandler(handshakeHandler);
    }
}
