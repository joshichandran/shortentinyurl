package com.payment.tiny.paymentlink.externalservice;

import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.tiny.paymentlink.model.BitlyResponse;
import com.payment.tiny.paymentlink.model.CreatePaymentResponse;
import com.payment.tiny.paymentlink.util.HttpClient;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Component
public class BitlyClient {

	class CallbackFuture extends CompletableFuture<Response> implements Callback {

		@Override
		public void onFailure(Call call, IOException e) {
			e.printStackTrace();

		}

		@Override
		public void onResponse(Call call, Response response) throws IOException {
			if (!response.isSuccessful()) {
				System.out.println("in failure");

			} else {
				//System.out.println("in sucess CallbackFuture");
				//ZonedDateTime endTime = ZonedDateTime.now();
				//System.out.println("Shorten Success : " + Thread.currentThread().getName() + " completed");
				//ObjectMapper objectMapper = new ObjectMapper();
				//BitlyResponse bitlyResponse = objectMapper.readValue(response.body().string(), BitlyResponse.class);
				//System.out.println(bitlyResponse);

				// ObjectMapper objectMapper= new ObjectMapper();
				// BitlyResponse bitlyResponse =
				// objectMapper.readValue(response.body().string(), BitlyResponse.class);

			}

		}
	}

	public String shortenPaymentUrl(String paymentUrl, String bearerAuth) throws Exception {

		ResponseEntity<BitlyResponse> response = null;
		String url = "https://api-ssl.bitly.com/v4/shorten";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		// headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(bearerAuth);

		Map<String, Object> map = new HashMap<>();
		map.put("domain", "bit.ly");
		map.put("long_url", paymentUrl);
		try {
			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
			response = restTemplate.postForEntity(url, entity, BitlyResponse.class);

		} catch (Exception e) {
			throw new Exception("BitlyClient rest template call failed");
		}

		if (response != null && response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("BitlyClient failed to call. Status is " + response.getStatusCode().value());
		}
		return response.getBody().getLink();
	}

	public String getShortenPyamentUrl(String paymentUrl, String bearerAuth, CreatePaymentResponse createPaymentResponse)
			throws  ExecutionException {
		String url = "https://api-ssl.bitly.com/v4/shorten1";

		System.out.println("Shorten : " + Thread.currentThread().getName());

		JSONObject jsonObject = new JSONObject();
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");

		try {
			jsonObject.put("domain", "bit.ly");
			jsonObject.put("long_url", paymentUrl);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RequestBody requestBody = new FormBody.Builder().add("domain", "bit.ly").add("long_url", paymentUrl).build();

		RequestBody body = RequestBody.create(JSON, jsonObject.toString());

		// Headers headers = new Headers();

		Request request = new Request.Builder().header("AUTHORIZATION", "Bearer " + bearerAuth).url(url).post(body)
				.build();
		
		CompletableFuture<BitlyResponse> future = call(url,bearerAuth, createPaymentResponse);
		System.out.println("before future : " + Thread.currentThread().getName());

		BitlyResponse bitlyResponse=null;
		try {
			bitlyResponse = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after future : " + Thread.currentThread().getName());

		System.out.println(bitlyResponse);
		// sync call
		// Response response=HttpClient.getHttpClient().newCall(request).execute();

		// async call

		/*
		 * ZonedDateTime startTime = ZonedDateTime.now(); Call call =
		 * HttpClient.getHttpClient().newCall(request); CallbackFuture callBackFuture =
		 * new CallbackFuture(); call.enqueue(callBackFuture); Response response; try {
		 * response = callBackFuture.get(); System.out.println("after future");
		 * ObjectMapper objectMapper = new ObjectMapper(); BitlyResponse bitlyResponse =
		 * objectMapper.readValue(response.body().string(), BitlyResponse.class);
		 * System.out.println(bitlyResponse);
		 * 
		 * } catch (InterruptedException | ExecutionException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (JsonMappingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		
		/*
		 * call.enqueue(new Callback(){
		 * 
		 * @Override public void onFailure(Call call, IOException e) {
		 * e.printStackTrace(); }
		 * 
		 * @Override public void onResponse(Call call, Response response) throws
		 * IOException {
		 * System.out.println("Shorten onResponse : "+Thread.currentThread().getName()+
		 * " completed");
		 * 
		 * if(!response.isSuccessful()) { System.out.println("in failure");
		 * 
		 * createPaymentResponse.setResponseCode(response.code());
		 * createPaymentResponse.setResponseMessage("Payment link failed"); }else {
		 * System.out.println("in sucess"); ZonedDateTime endTime=ZonedDateTime.now();
		 * System.out.println("Time :"+Duration.between(startTime, endTime).toMillis());
		 * System.out.println("Shorten Success : "+Thread.currentThread().getName()+
		 * " completed");
		 * 
		 * 
		 * ObjectMapper objectMapper= new ObjectMapper(); BitlyResponse bitlyResponse =
		 * objectMapper.readValue(response.body().string(), BitlyResponse.class);
		 * createPaymentResponse.setResponseCode(response.code());
		 * createPaymentResponse.setPaymentUrl(bitlyResponse.getLink());
		 * createPaymentResponse.setResponseMessage("Payment link sucessful");
		 * createPaymentResponse.setPaymentLinkId(1234L); } }
		 * 
		 * });
		 */

		System.out.println("in fn");
		
		return "test";
	}
	
	private CompletableFuture<BitlyResponse> call(String url, String bearerAuth,CreatePaymentResponse createPaymentResponse) {
	    CompletableFuture<BitlyResponse> future = new CompletableFuture<>();

	    OkHttpClient client =  HttpClient.getHttpClient();
	  //  String url = "https://api-ssl.bitly.com/v4/shorten";

		System.out.println("Shorten : " + Thread.currentThread().getName());

		JSONObject jsonObject = new JSONObject();
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");

		try {
			jsonObject.put("domain", "bit.ly");
			jsonObject.put("long_url", "https://google.com");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RequestBody body = RequestBody.create(JSON, jsonObject.toString());

		// Headers headers = new Headers();

		Request request = new Request.Builder().header("AUTHORIZATION", "Bearer " + bearerAuth).url(url).post(body)
				.build();
	    
	    client.newCall(request).enqueue(new Callback() {
	        @Override
	        public void onFailure(Call call, IOException e) {
	            future.completeExceptionally(e);
	        }

	        @Override
	        public void onResponse(Call call, Response response) {
	            try {
	        		System.out.println("response  : " + Thread.currentThread().getName());

	                ResponseBody body = response.body();
	                if (response.code() != 200 || body == null) {
	                	createPaymentResponse.setResponseCode(response.code());
	                	createPaymentResponse.setResponseMessage("Payment link failed");
	                    throw new IOException("Http error");
	                } else {
	                	ObjectMapper objectMapper= new ObjectMapper(); 
	                	BitlyResponse bitlyResponse =
	                			  objectMapper.readValue(response.body().string(), BitlyResponse.class);
	                    future.complete(bitlyResponse);
	                    createPaymentResponse.setResponseCode(response.code());
	                    createPaymentResponse.setResponseMessage("Payment link sucessful");
	                    createPaymentResponse.setPaymentUrl(bitlyResponse.getLink());
	                }
	            } catch (  Exception e) {
	                future.completeExceptionally(e);
	            }
	        }
	    });

	    return future;
	}

}
