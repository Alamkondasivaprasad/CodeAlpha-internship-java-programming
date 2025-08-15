import java.util.*;
public class AIChatbot {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, String> faq = new HashMap<>();
    private static final String[] fallbackResponses = {
        "Interesting... tell me more.",
        "Why do you think that?",
        "Can you elaborate?",
        "Hmm, I see. Go on.",
        "That's cool!"
    };
    private static final Random random = new Random();

    public static void main(String[] args) {
        initFAQs();

        System.out.println("🤖 AI Chatbot: Hello! How can I help you today? (type 'bye' to exit)");

        while (true) {
            System.out.print("You: ");
            String userInput = sc.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("🤖 AI Chatbot: Goodbye! Have a great day!");
                break;
            }

            String response = getResponse(userInput);
            System.out.println("🤖 AI Chatbot: " + response);
        }
    }

    // Initialize Frequently Asked Questions
    private static void initFAQs() {
        faq.put("hello", "Hi there! How can I assist you?");
        faq.put("hi", "Hello! What’s up?");
        faq.put("how are you", "I’m just code, but I’m doing great! 😄");
        faq.put("what is your name", "I’m your friendly Java chatbot.");
        faq.put("what can you do", "I can chat with you and answer basic questions.");
        faq.put("bye", "Goodbye! Talk to you later.");
        faq.put("help", "You can ask me about myself, greet me, or say 'bye' to exit.");
    }

    // Get response based on user input
    private static String getResponse(String input) {
        String normalized = input.toLowerCase();

        // Keyword-based match
        for (String key : faq.keySet()) {
            if (normalized.contains(key)) {
                return faq.get(key);
            }
        }

        // Simple NLP: Token matching
        List<String> tokens = Arrays.asList(normalized.split("\\s+"));
        for (String token : tokens) {
            if (faq.containsKey(token)) {
                return faq.get(token);
            }
        }

        // Fallback: Random generic response
        return fallbackResponses[random.nextInt(fallbackResponses.length)];
    }
}
