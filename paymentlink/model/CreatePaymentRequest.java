package com.payment.tiny.paymentlink.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePaymentRequest {

	@JsonProperty("MERCHANT_ID")
	private Integer merchantId;
	
	@JsonProperty("MERCHANT_ACCESS_CODE")
	private String merchantAccessCode;
	
	@JsonProperty("REFERENCE_NO")
	private String referenceNo;
	
	@JsonProperty("AMOUNT")
	private Long amount;
	
	@JsonProperty("PRODUCT_DESCRIPTION")
	private String productDescription;
	
	@JsonProperty("PRODUCT_CODE")
	private String productCode;
	
	@JsonProperty("CUSTOMER_MOBILE_NO")
	private String customerMobileNo;
	
	@JsonProperty("CUSTOMER_EMAIL_ID")
	private String customerEmailid;

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantAccessCode() {
		return merchantAccessCode;
	}

	public void setMerchantAccessCode(String merchantAccessCode) {
		this.merchantAccessCode = merchantAccessCode;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCustomerMobileNo() {
		return customerMobileNo;
	}

	public void setCustomerMobileNo(String customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public String getCustomerEmailid() {
		return customerEmailid;
	}

	public void setCustomerEmailid(String customerEmailid) {
		this.customerEmailid = customerEmailid;
	}
}
