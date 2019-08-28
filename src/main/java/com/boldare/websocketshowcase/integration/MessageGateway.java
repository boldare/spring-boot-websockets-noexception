package com.boldare.websocketshowcase.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

	@Gateway(requestChannel = "messageFromWebsocket")
	void processMessage(String message);
}
