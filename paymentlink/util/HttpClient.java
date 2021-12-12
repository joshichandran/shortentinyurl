package com.payment.tiny.paymentlink.util;

import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;


@Component
public class HttpClient {
	
	private static final OkHttpClient client = new OkHttpClient();
	
	public static OkHttpClient getHttpClient() {
		return client;
	}


}
