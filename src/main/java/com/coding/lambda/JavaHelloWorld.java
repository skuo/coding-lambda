package com.coding.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Lambda function that simply prints "Hello World" if the input String is not
 * provided, otherwise, print "Hello " with the provided input String.
 */
public class JavaHelloWorld implements RequestHandler<Request, Response> {
	@Override
	public Response handleRequest(Request request, Context context) {
		context.getLogger().log(request.firstName + " " + request.lastName);
		String greetingStr = String.format("Hello %s %s.", request.firstName, request.lastName);
		return new Response(greetingStr);		
	}
}
