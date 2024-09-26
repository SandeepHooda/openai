package com.sandeep.AI.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandeep.AI.Util.SCV;
import com.sandeep.AI.entity.Employee;
import com.sandeep.AI.entity.EmployeeRepo;

import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpenAIService {
    

    @Value("${openai.api.url}")
    private String apiUrl;
    
  
    
    @Autowired
    private EmployeeRepo empRepo;
    
    @Autowired
    private EntityManager em;
    

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String callGpt4(String context, String prompt) {
    	List<Employee> empList = empRepo.findAll();
    	log.info("Employee List "+empList);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(SCV.getValue(SCV.OPEN_AI_API_KEY));

        Map<String, Object> requestBody = new HashMap();
        requestBody.put("model", "gpt-4");
    
        requestBody.put("messages", List.of(Map.of("role", "system", "content", prompt )
        		,Map.of("role", "user", "content", prompt)));

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
            return extractResponseFromBody(response.getBody());
        } catch (RestClientException e) {
            throw new RuntimeException("Error calling GPT-4 API", e);
        }
    }

    private String extractResponseFromBody(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing GPT-4 response", e);
        }
    }
}

