package com.practice.aichatagent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AiChatAgentApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AiChatAgentApplication.class, args);
        ChatAgent agent = new ChatAgent();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("   AI System Design Agent    ");
        System.out.println("=============================");
        System.out.println("Commands:");
        System.out.println("  /design <system>  → design any system");
        System.out.println("  /review <code>    → review your code");
        System.out.println("  /explain <code>   → explain code");
        System.out.println("  /clear            → reset memory");
        System.out.println("  /history          → see memory size");
        System.out.println("  exit              → quit");
        System.out.println("=============================\n");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().trim();

            if (input.isBlank()) continue;
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bye!");
                break;
            }

            System.out.println("\nAI: " + agent.chat(input));
            System.out.println();
        }


    }
}
