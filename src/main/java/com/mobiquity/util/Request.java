package com.mobiquity.util;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Request<T> implements Serializable{

	private static final long serialVersionUID = -2164998403055383271L;

	@NotNull(message="Header must be not null ")
	@Valid
	private HeaderModel header;
	
	@NotNull(message="payload must be not null ")
	@Valid
	private T payload;

	public HeaderModel getHeader() {
		return header;
	}

	public void setHeader(final HeaderModel header) {
		this.header = header;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(final T payload) {
		this.payload = payload;
	}
	
	
	

}
