package com.smartfaq.data;
import java.util.*;
public class FAQLoader {
    public record FAQ(String id, String question, String answer) {}
    public static List<FAQ> loadFAQs() {
        return List.of(
            new FAQ("faq1", "How do I reset my password?", "Go to the login page and click Forgot Password. Enter your email and follow the instructions."),
            new FAQ("faq2", "What payment methods do you accept?", "We accept Visa, MasterCard, Amex, PayPal, UPI, and bank transfers."),
            new FAQ("faq3", "How can I track my order?", "Visit My Orders in your account dashboard and click on your order to see real-time tracking."),
            new FAQ("faq4", "What is your return policy?", "You can return any product within 30 days of delivery for a full refund."),
            new FAQ("faq5", "How do I contact customer support?", "Reach us via live chat, email at support@example.com, or call 1800-123-456."),
            new FAQ("faq6", "Is my personal data safe?", "Yes, we use AES-256 encryption and never sell your data to third parties."),
            new FAQ("faq7", "Can I change my order after placing it?", "Orders can be modified within 1 hour of placement via live chat."),
            new FAQ("faq8", "Do you offer student discounts?", "Yes! Students get 20% off with a valid student email address."),
            new FAQ("faq9", "How long does shipping take?", "Standard shipping takes 5-7 business days. Express shipping takes 1-2 days."),
            new FAQ("faq10", "How do I cancel my subscription?", "Go to Account Settings > Subscription > Cancel Plan.")
        );
    }
}
