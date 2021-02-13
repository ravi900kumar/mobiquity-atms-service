package com.mobiquity.util;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("header")
public class HeaderModel implements Serializable {
 
	private static final long serialVersionUID = 1L;
	@NotNull(message = "Application Name must not null")
	private String appName;
	private String userId;
	private String responseDateTime;
	private String language;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getResponseDateTime() {
		return responseDateTime;
	}
	public void setResponseDateTime(String responseDateTime) {
		this.responseDateTime = responseDateTime;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "HeaderModel [appName=" + appName + ", userId=" + userId + ", responseDateTime=" + responseDateTime
				+ ", language=" + language + "]";
	}
	
	
}
