package com.payment.tiny.paymentlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.tiny.paymentlink.externalservice.BitlyClient;
import com.payment.tiny.paymentlink.model.CreatePaymentRequest;
import com.payment.tiny.paymentlink.model.CreatePaymentResponse;

@Service("paymentLinkcreationservice")
public class PaymentLinkCreationService {

	@Autowired
	private BitlyClient bitlyClient;

	public CreatePaymentResponse createPaymentResponse(CreatePaymentRequest request)  {

		String shortUrl = null;
		// Change the hardcoded value
		CreatePaymentResponse response = new CreatePaymentResponse();
		System.out.println("Service : "+Thread.currentThread().getName());

		System.out.println("in start");
		try {
			 String ret=bitlyClient.getShortenPyamentUrl(
					"http://127.0.0.1:8030/EIPS/ProcessingEipsRequest?args=AAEF3E1E29CF35EE78C94742D29DF3391BBDA907600F759C32857A6F3F929646CD186B91C5A0D252",
					"d54204456c059c1436a674c1792a957e4320e0de",response);
				System.out.println("Service after : "+Thread.currentThread().getName());

			 response.setResponseMessage(ret);
			 System.out.println("return value :"+ret);

		} catch (Exception e) {
		}
		System.out.println("Service end 123: "+Thread.currentThread().getName());

		System.out.println("in end");

		return response;
	}

}
