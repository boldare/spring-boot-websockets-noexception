package com.boldare.websocketshowcase.websocket.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@Slf4j
public class WebsocketClient {
    public static void main(String[] args) throws InterruptedException {

        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(
                URI.create("ws://localhost:8099/ocpp/1231231"),
                session -> session.send(
                        Mono.just(session.textMessage("event-spring-reactive-client-websocket")))
                        .thenMany(session.receive()
                                .map(WebSocketMessage::getPayloadAsText)
                                .log())
                        .then())
                .block(Duration.ofSeconds(10));
    }

}
