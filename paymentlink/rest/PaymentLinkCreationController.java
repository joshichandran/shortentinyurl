package com.payment.tiny.paymentlink.rest;

import java.util.concurrent.CompletableFuture;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.payment.tiny.paymentlink.model.CreatePaymentRequest;
import com.payment.tiny.paymentlink.model.CreatePaymentResponse;
import com.payment.tiny.paymentlink.service.PaymentLinkCreationService;

@RestController
@RequestMapping("/api/PaymentURL")
public class PaymentLinkCreationController {

	@Autowired
	private PaymentLinkCreationService paymentLinkCreationService;

	@PostMapping("CreatePaymentURL")

	@Consumes(MediaType.APPLICATION_JSON)

	@Produces(MediaType.APPLICATION_JSON)
	public CreatePaymentResponse createPaymentResponse(@RequestBody CreatePaymentRequest request

			) throws Exception {

		DeferredResult<CreatePaymentResponse> response = new DeferredResult<>();
		System.out.println("Main : "+Thread.currentThread().getName());
		CreatePaymentResponse createPaymentResponse = paymentLinkCreationService.createPaymentResponse(request);

//		CompletableFuture.supplyAsync(() -> paymentLinkCreationService.createPaymentResponse(request)
//
//		).whenComplete((result, throwable) -> {
//			System.out.println("in result");
//			System.out.println("Main Complete: "+Thread.currentThread().getName());
//
//			response.setResult(result);
//		});

		return createPaymentResponse;
	}

	/*
	 * @PostMapping("CreatePaymentURL")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public void
	 * createPaymentResponse(@RequestBody CreatePaymentRequest request,
	 * 
	 * @Suspended AsyncResponse asyncResponse) throws Exception {
	 * 
	 * 
	 * DeferredResult<CreatePaymentResponse> response = new DeferredResult<>();
	 * 
	 * CompletableFuture.supplyAsync(() ->
	 * paymentLinkCreationService.createPaymentResponse(request)
	 * 
	 * ).whenComplete((result, throwable) -> { System.out.println("in result");
	 * response.setResult(result); });
	 * 
	 * 
	 * 
	 * new Thread() { public void run() { CreatePaymentResponse response =
	 * paymentLinkCreationService.createPaymentResponse(request); Response response1
	 * = Response.ok(response, MediaType.APPLICATION_XML_TYPE) .build();
	 * asyncResponse.resume(response1); } }.start();
	 * 
	 * }
	 */
}
