package com.payment.tiny.paymentlink.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePaymentResponse {
	
	@JsonProperty("RESPONSE_CODE")
	private Integer responseCode;
	
	@JsonProperty("RESPONSE_MESSAGE")
	private String responseMessage;
	
	@JsonProperty("PAYMENT_LINK_ID")
	private Long paymentLinkId;
	
	@JsonProperty("PAYMENT_URL")
	private String paymentUrl;

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Long getPaymentLinkId() {
		return paymentLinkId;
	}

	public void setPaymentLinkId(Long paymentLinkId) {
		this.paymentLinkId = paymentLinkId;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	
}
