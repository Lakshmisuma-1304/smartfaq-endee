package com.smartfaq;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) { System.out.println("Set GEMINI_API_KEY"); System.exit(1); }
        System.out.println("==============================================");
        System.out.println("     SmartFAQ - Semantic FAQ Chatbot");
        System.out.println("     Powered by Endee Vector Database");
        System.out.println("==============================================");
        EmbeddingService embeddingService = new EmbeddingService(apiKey);
        EndeeService endeeService = new EndeeService(embeddingService);
        System.out.println("Initializing FAQ database...");
        endeeService.initialize();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ask me anything! (type exit to quit)");
        while (true) {
            System.out.print("You: ");
            String question = scanner.nextLine().trim();
            if (question.equalsIgnoreCase("exit")) { System.out.println("Goodbye!"); break; }
            if (question.isEmpty()) continue;
            System.out.println(endeeService.findAnswer(question));
        }
    }
}
