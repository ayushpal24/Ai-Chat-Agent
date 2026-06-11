package com.practice.aichatagent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatAgent {
    // memory — this is what makes it conversational
    private final List<Map<String, String>> history = new ArrayList<>();

    private final String systemPrompt = """
            You are an expert software engineer and system design mentor.
            Help with LLD, HLD, DS/Algo, Java, and system design questions.
            Give concise answers with code examples when needed.
            """;

    public String chat(String userMessage) {
        // 1. check if it's a tool/command
        if (userMessage.startsWith("/")) {
            return handleTool(userMessage);
        }

        // 2. add user message to memory
        history.add(Map.of("role", "user", "content", userMessage));

        // 3. call LLM with full history
        String response = OpenAIClient.call(systemPrompt, history);

        // 4. add response to memory (crucial for next turn)
        history.add(Map.of("role", "assistant", "content", response));

        return response;
    }

    // --- AGENTIC PART — tools the agent can use ---
    private String handleTool(String input) {
        if (input.startsWith("/design ")) {
            String system = input.substring(8);
            return chat("Design " + system + " covering HLD, LLD, DB schema, bottlenecks");
        }

        if (input.startsWith("/review ")) {
            String code = input.substring(8);
            return chat("Review this code for thread safety, design patterns, performance:\n" + code);
        }

        if (input.startsWith("/explain ")) {
            String code = input.substring(9);
            return chat("Explain this code step by step:\n" + code);
        }

        if (input.equals("/clear")) {
            history.clear();
            return "Memory cleared!";
        }

        if (input.equals("/history")) {
            return "Messages in memory: " + history.size();
        }

        return "Unknown command.";
    }
}
