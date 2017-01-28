package com.coding.lambda;

import java.util.Map;

public class FacadeRequest {
	private Map<String,String> body;
	private Map<String,Map<String,String>> params;
	private Map<String,String> stage;
	private Map<String,String> context;
	
	public Map<String, String> getBody() {
		return body;
	}
	public void setBody(Map<String, String> body) {
		this.body = body;
	}
	public Map<String, Map<String, String>> getParams() {
		return params;
	}
	public void setParams(Map<String, Map<String, String>> params) {
		this.params = params;
	}
	public Map<String, String> getStage() {
		return stage;
	}
	public void setStage(Map<String, String> stage) {
		this.stage = stage;
	}
	public Map<String, String> getContext() {
		return context;
	}
	public void setContext(Map<String, String> context) {
		this.context = context;
	}
	
}
