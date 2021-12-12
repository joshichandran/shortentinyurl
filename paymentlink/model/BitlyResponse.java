package com.payment.tiny.paymentlink.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitlyResponse {
	
	@Override
	public String toString() {
		return "BitlyResponse [createdAt=" + createdAt + ", id=" + id + ", link=" + link + ", longUrl=" + longUrl + "]";
	}

	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("link")
	private String link;
	
	@JsonProperty("long_url")
	private String longUrl;

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	

}
