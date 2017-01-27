package com.coding.lambda;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {

	@Test
	public void testJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String,String> map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		
		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter, map);
		String jsonStr = stringWriter.toString();
		String expStr = "{\"k1\":\"v1\",\"k2\":\"v2\"}"; 
		assertEquals(expStr, jsonStr);
		
		Map<String,String> readMap = new HashMap<>();
		readMap = objectMapper.readValue(expStr, new TypeReference<HashMap<String,String>>() {});
		int asserted = 0;
		for (Map.Entry<String, String> entry : readMap.entrySet()) {
			if (entry.getKey().equals("k1")) {
				assertEquals("v1", entry.getValue());
				asserted++;
			}
			if (entry.getKey().equals("k2")) {
				assertEquals("v2", entry.getValue());
				asserted++;
			}
		}
		assertEquals(2, asserted);
	}
}
