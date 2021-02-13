package com.mobiquity.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response<T> implements Serializable  {
	private HeaderModel header;
	@JsonProperty(value="responseBody")
	private T response;
	public HeaderModel getHeader() {
		return header;
	}
	public void setHeader(final HeaderModel header) {
		this.header = header;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(final T response) {
		this.response = response;
	}
	
	

}
