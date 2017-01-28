package com.coding.lambda;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;

public class HelloWorldFacade {

	/* The expected json
{
	"pathParams":
	{
		"name": "bright"
	}
	"queryParams":
	{
		"breed": "curly coded retriever"
	}
	"body": 
  	{
		"firstName": "DogVacay",
		"lastName": "Facade",
  	}
}
	 */
	public Response handleRequest(FacadeRequest request, Context context) {
		Map<String,String> body = request.getBody();
		Map<String,String> queryParams = request.getQueryParams();
		Map<String,String> pathParams = request.getPathParams();
		context.getLogger().log(body.get("firstName") + " " + body.get("lastName") + ", name=" + pathParams.get("name") +
				"breed" + queryParams.get("breed"));
		String greetingStr = String.format("Body %s %s. queryParams[name]=%s, pathParams[breed]=%s", 
				body.get("firstName"), body.get("lastName"),
				pathParams.get("name"),
				queryParams.get("breed"));
		return new Response(greetingStr);		
	}

}
