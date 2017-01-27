package com.coding.lambda;

import com.amazonaws.services.lambda.runtime.Context;

public class HelloWorldFacade {

	public Response handleRequest(Request request, Context context) {
		context.getLogger().log(request.getFirstName() + " " + request.getLastName() + ", name=" + request.getName());
		String greetingStr = String.format("Hello %s %s. name=%s", request.getFirstName(), request.getLastName(),
				request.getName());
		return new Response(greetingStr);		
	}

}
