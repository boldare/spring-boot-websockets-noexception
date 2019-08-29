package com.boldare.websocketshowcase.websocket;

import com.boldare.websocketshowcase.integration.MessageGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactiveWebsocketConfiguration {

    private final MessageGateway gateway;

    @Bean
    public HandlerMapping handlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setUrlMap(
                Collections.singletonMap("/ocpp/{chargerName}", webSocketHandler()));
        simpleUrlHandlerMapping.setCorsConfigurations(
                Collections.singletonMap("*", new CorsConfiguration().applyPermitDefaultValues()));
        simpleUrlHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return simpleUrlHandlerMapping;
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    private String ackId = null;

    private WebSocketHandler webSocketHandler() {
        return session ->
                session.send(
                        //send ack
                        Flux.interval(Duration.ofSeconds(1))
                                .map(Object::toString)
                                .map(payload -> session.textMessage("[3," + ackId + ", {}]"))
                ).and(session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .doOnNext(payload -> {
                            ackId = payload.split(",")[1];
                            gateway.processMessage(payload);
                        })
                );
    }

}
