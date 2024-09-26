package com.sandeep.AI.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.AI.service.OpenAIService;
import com.sandeep.AI.vo.Prompt;

@RestController
@RequestMapping("/api/gpt4")
public class GPT4Controller {

    private final OpenAIService openAIService;

    public GPT4Controller(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping( path="/ask", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> askGpt4(@RequestBody Prompt prompt ) {
    	System.out.println(" Hi.....");
    	//context "You will be provided with a question, and your task is to write java code for that."
        //String prompt = "what does these line do:  List<Integer> sortedList = numbers.stream().sorted().collect(Collectors.toList())";// request.get("prompt");
        String gptResponse = openAIService.callGpt4( prompt.getContext(), prompt.getRequest());
        return ResponseEntity.ok(gptResponse);
    }
}

