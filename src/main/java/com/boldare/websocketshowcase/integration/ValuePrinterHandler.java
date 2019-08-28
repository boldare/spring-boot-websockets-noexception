package com.boldare.websocketshowcase.integration;

import com.boldare.websocketshowcase.service.ValuePrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValuePrinterHandler implements MessageHandler {

	private final ValuePrinterService valuePrinterService;

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		Integer payload = (Integer) message.getPayload();
		valuePrinterService.print(payload);
	}
}
