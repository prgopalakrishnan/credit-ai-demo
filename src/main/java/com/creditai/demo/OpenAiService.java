package com.creditai.demo;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OpenAiService {

    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String getAdvice(String prompt) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.out.println("OpenAI API key not set. Using mocked response.");
            return mockedResponse();
        }

        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("model", "gpt-3.5-turbo"); // safer, more available than gpt-4
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a helpful financial advisor AI."));
        messages.put(new JSONObject().put("role", "user").put("content", prompt));
        json.put("messages", messages);

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("OpenAI API call failed: " + response.code() + " " + response.message());
                return mockedResponse();
            }
            return response.body().string();
        } catch (IOException e) {
            System.out.println("Error calling OpenAI API: " + e.getMessage());
            return mockedResponse();
        }
    }

    private String mockedResponse() {
        // Return a static example plan
        return """
        {
          "planId": "plan_001",
          "summary": "Reduce credit utilization below 30% and pay off high-interest debt first",
          "steps": [
            "Shift $1200 to low-balance credit card",
            "Generate dispute for erroneous collection",
            "Set up automatic payments to avoid late fees"
          ]
        }
        """;
    }
}

