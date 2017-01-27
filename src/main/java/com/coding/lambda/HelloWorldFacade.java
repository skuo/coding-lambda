package com.coding.lambda;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;

public class HelloWorldFacade {

	public Response handleRequest(Map<String,String> request, Context context) {
		context.getLogger().log(request.get("firstName") + " " + request.get("lastName") + ", name=" + request.get("name"));
		String greetingStr = String.format("Hello %s %s. name=%s", request.get("firstName"), request.get("lastName"),
				request.get("name"));
		return new Response(greetingStr);		
	}

}
