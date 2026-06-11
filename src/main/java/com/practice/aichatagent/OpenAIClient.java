package com.practice.aichatagent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class OpenAIClient {
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String  call(String systemPrompt,
                              List<Map<String, String>> history) {
        try {
            JSONArray messages = new JSONArray();

            // system prompt as first message
            messages.put(new JSONObject()
                    .put("role", "system")
                    .put("content", systemPrompt));

            // conversation history
            for (Map<String, String> msg : history) {
                messages.put(new JSONObject()
                        .put("role", msg.get("role"))
                        .put("content", msg.get("content")));
            }

            String body = new JSONObject()
                    .put("model", "llama-3.1-8b-instant")
                    .put("max_tokens", 1024)
                    .put("messages", messages)
                    .toString();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("RAW RESPONSE: " + response.body());

            // parse response
            return new JSONObject(response.body())
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
