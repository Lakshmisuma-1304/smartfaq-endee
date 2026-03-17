package com.smartfaq;
import com.smartfaq.data.FAQLoader;
import io.endee.client.Endee;
import io.endee.client.Index;
import io.endee.client.exception.EndeeApiException;
import io.endee.client.types.*;
import java.io.IOException;
import java.util.*;
public class EndeeService {
    private static final String INDEX_NAME = "faq_index";
    private static final int DIMENSION = 3072;
    private final Endee client;
    private final EmbeddingService embeddingService;
    private Index index;
    public EndeeService(EmbeddingService embeddingService) throws EndeeApiException {
        this.client = new Endee();
        this.embeddingService = embeddingService;
    }
    public void initialize() throws EndeeApiException, IOException {
        try { client.deleteIndex(INDEX_NAME); } catch (Exception ignored) {}
        CreateIndexOptions options = CreateIndexOptions.builder(INDEX_NAME, DIMENSION).spaceType(SpaceType.COSINE).precision(Precision.FLOAT32).build();
        client.createIndex(options);
        this.index = client.getIndex(INDEX_NAME);
        List<FAQLoader.FAQ> faqs = FAQLoader.loadFAQs();
        List<VectorItem> vectors = new ArrayList<>();
        System.out.println("Embedding FAQs... please wait...");
        for (FAQLoader.FAQ faq : faqs) {
            double[] embedding = embeddingService.getEmbedding(faq.question());
            vectors.add(VectorItem.builder(faq.id(), embedding).meta(Map.of("question", faq.question(), "answer", faq.answer())).build());
            System.out.println("  Embedded: " + faq.question());
        }
        index.upsert(vectors);
        System.out.println("\nAll FAQs loaded into Endee!\n");
    }
    public String findAnswer(String userQuestion) throws IOException, EndeeApiException {
        double[] queryVector = embeddingService.getEmbedding(userQuestion);
        List<QueryResult> results = index.query(QueryOptions.builder().vector(queryVector).topK(1).build());
        if (results.isEmpty()) return "Sorry, I could not find a relevant answer.";
        QueryResult top = results.get(0);
        Map<String, Object> meta = top.getMeta();
        return String.format("\nMatched FAQ: %s\nConfidence: %.1f%%\nAnswer: %s\n", meta.get("question"), top.getSimilarity() * 100, meta.get("answer"));
    }
}