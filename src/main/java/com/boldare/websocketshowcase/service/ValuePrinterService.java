package com.boldare.websocketshowcase.service;

import org.springframework.stereotype.Service;

@Service
public class ValuePrinterService {

	public void print(final Integer value) {
		System.out.println("The size of incoming message is " + value);
	}

	public void printText(final String text) {
		System.out.println("The incoming message is " + text);
	}
}
