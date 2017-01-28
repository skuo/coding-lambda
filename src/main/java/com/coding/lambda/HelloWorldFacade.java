package com.coding.lambda;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class HelloWorldFacade {

	/* The expected json
{
  "body": {
	"firstName": "DogVacay",
	"lastName": "Facade",
  }
  "parameters" : {
     "path" : {    
       "path_name" : "path_value", 
       ...
     }
     "header" : {  
       "header_name" : "header_value",
       ...
     }
     'querystring" : {
       "querystring_name" : "querystring_value",
       ...
     }
   },
   "stage" : {
     
   },
  "context" : {
    "account-id" : "$context.identity.accountId",
    "api-id" : "$context.apiId",
    "api-key" : "$context.identity.apiKey",
    "authorizer-principal-id" : "$context.authorizer.principalId",
    "caller" : "$context.identity.caller",
    "cognito-authentication-provider" : "$context.identity.cognitoAuthenticationProvider",
    "cognito-authentication-type" : "$context.identity.cognitoAuthenticationType",
    "cognito-identity-id" : "$context.identity.cognitoIdentityId",
    "cognito-identity-pool-id" : "$context.identity.cognitoIdentityPoolId",
    "http-method" : "$context.httpMethod",
    "stage" : "$context.stage",
    "source-ip" : "$context.identity.sourceIp",
    "user" : "$context.identity.user",
    "user-agent" : "$context.identity.userAgent",
    "user-arn" : "$context.identity.userArn",
    "request-id" : "$context.requestId",
    "resource-id" : "$context.resourceId",
    "resource-path" : "$context.resourcePath"
  }
}
	 */
	public Response handleRequest(FacadeRequest request, Context context) {
		LambdaLogger logger = context.getLogger();
		// body
		Map<String, String> body = request.getBody();
		logReqMap("body", body, logger);
		// parameters
		Map<String,Map<String,String>> parameters = request.getParams();
		logMapOfReqMap("params", parameters, logger);
		// stage
		Map<String,String> stage = request.getStage();
		logReqMap("stage", stage, logger);
		// context
		Map<String,String> ctx = request.getContext();
		logReqMap("context", ctx, logger);
		
		// construct response
		String greetingStr = String.format("Body %s %s. params.path[name]=%s, params.querystring[breed]=%s", 
				body.get("firstName"), body.get("lastName"),
				parameters.get("path").get("name"),
				parameters.get("querystring").get("breed"));
		return new Response(greetingStr);		
	}

	private void logMapOfReqMap(String attr, Map<String,Map<String,String>> mapOfReqMap, LambdaLogger logger) {
		// error check on null
		if (mapOfReqMap == null)
			return;
		for (Map.Entry<String, Map<String,String>> entry: mapOfReqMap.entrySet()) {
			logReqMap(attr + " " + entry.getKey(), entry.getValue(), logger);
		}
	}

	private void logReqMap(String attr, Map<String,String> reqMap, LambdaLogger logger) {
		// error check on null
		if (reqMap == null)
			return;
		
		StringBuilder sb = new StringBuilder("[" + attr + "]   ");
		for (Map.Entry<String, String> e : reqMap.entrySet())
			sb.append(e.getKey()+":"+e.getValue() + ", ");
		logger.log(sb.toString());
	}

}
