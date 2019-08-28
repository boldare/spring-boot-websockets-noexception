package com.boldare.websocketshowcase.integration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.StringUtils;

@Configuration
public class WebsocketIntegrationFlow {

	@Bean
	public PublishSubscribeChannel messageFromWebsocket() {
		return new PublishSubscribeChannel();
	}

	@Transformer(inputChannel = "messageFromWebsocket", outputChannel = "TRANSFORMED_MESSAGE_FROM_WS")
	Integer transform(final String payload) {
		if (StringUtils.isEmpty(payload)) {
			return 0;
		} else {
			return payload.length();
		}
	}

	@Bean
	@ServiceActivator(inputChannel = "TRANSFORMED_MESSAGE_FROM_WS")
	MessageHandler handle(final ValuePrinterHandler valuePrinterHandler) {
		return valuePrinterHandler;
	}


	@Bean
	@ServiceActivator(inputChannel = "messageFromWebsocket")
	MessageHandler handleValuePrinterHandler(final ValuePrinterTextHandler valuePrinterTextHandler) {
		return valuePrinterTextHandler;
	}


}
