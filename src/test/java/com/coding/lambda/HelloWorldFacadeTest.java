package com.coding.lambda;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloWorldFacadeTest {
    @Mock
    Context mockContext;

    @Mock
    LambdaLogger mockLambdaLogger;
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
	@Test
	public void testHelloWorldFacade() throws JsonParseException, JsonMappingException, IOException {
		String jsonStr = 
"{" +
"\"params\": {" + 
  "\"path\": {" +
    "\"name\": \"bright\"" +
  "}," +
  "\"querystring\": {" +
    "\"breed\": \"curly coated retriever\"" +  
  "}" +
"}," +
"\"body\": {" +
    "\"firstName\": \"DogVacay\"," +
    "\"lastName\": \"Facade\"" +
  "}," +
"\"context\": {" +
    "\"account-id\": \"account-id\"" +
  "}" +
"}\"" +
	"}" +
"}"
;
		// configure mock objects
		when(mockContext.getLogger()).thenReturn(mockLambdaLogger);
		
        ObjectMapper objectMapper = new ObjectMapper();
        FacadeRequest facadeRequest = objectMapper.readValue(jsonStr, FacadeRequest.class);
		HelloWorldFacade facade = new HelloWorldFacade();
		Response response = facade.handleRequest(facadeRequest, mockContext);
        assertEquals("Body DogVacay Facade. params.path[name]=bright, params.querystring[breed]=curly coated retriever",
                response.getGreetings());
	}
}
