package com.coding.lambda;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloWorldFacade {
    private static ObjectMapper objectMapper = new ObjectMapper();

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
		Map<String, String> requestBody = request.getBody();
		logReqMap("body", requestBody, logger);
		// parameters
		Map<String,Map<String,String>> requestParams = request.getParams();
		logMapOfReqMap("params", requestParams, logger);
		// stage
		Map<String,String> requestStage = request.getStage();
		logReqMap("stage", requestStage, logger);
		// context
		Map<String,String> requestCtx = request.getContext();
		logReqMap("context", requestCtx, logger);
		
		// call other endpoints
		String getBody = doGet("https://41588xzcia.execute-api.us-west-2.amazonaws.com/sandbox/nodelambda", logger);
        String postBody = doPost("https://41588xzcia.execute-api.us-west-2.amazonaws.com/sandbox/javalambda/Terry?aBreed=Poodle",
                requestBody, logger);
		
		// construct response
		String greetingStr = String.format("Body %s %s. params.path[name]=%s, params.querystring[breed]=%s", 
				requestBody.get("firstName"), requestBody.get("lastName"),
				requestParams.get("path").get("name"),
				requestParams.get("querystring").get("breed"));
		
		return new Response(greetingStr, Arrays.asList(getBody,postBody));		
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

	private String doGet(String url, LambdaLogger logger) {
	    logger.log("doGet: " + url);
	    String body = null;
	    CloseableHttpClient client = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(url);
	    CloseableHttpResponse response = null;
	    try {
	        response = client.execute(httpGet);
	        int status = response.getStatusLine().getStatusCode();
	        // return null for status not 2xx
	        if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                body = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
	        }
        } catch (ClientProtocolException cpe) {
            // how to log info, error and etc.
            logger.log(cpe.getMessage());
        } catch (IOException ioe) {
            logger.log(ioe.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                    client.close();
                } catch (IOException ioe) {
                    logger.log(ioe.getMessage());
                }
            }
        }	    
	    return body;
	}
	
	private String doPost(String url, Map<String,String> requestBody, LambdaLogger logger) {
	    String body = null;
        CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
	    
        CloseableHttpResponse response = null;
	    try {
	        StringEntity jsonPayload = new StringEntity(objectMapper.writeValueAsString(requestBody));
            httpPost.setEntity(jsonPayload);
            response = client.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            // return null for status not 2xx
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                body = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
        } catch (ClientProtocolException cpe) {
            // how to log info, error and etc.
            logger.log(cpe.getMessage());
        } catch (IOException ioe) {
            logger.log(ioe.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                    client.close();
                } catch (IOException ioe) {
                    logger.log(ioe.getMessage());
                }
            }
        }       	    
	    return body;
	}
}
