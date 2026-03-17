package com.smartfaq;
import com.google.gson.*;
import okhttp3.*;
import java.io.IOException;
import java.util.*;
public class EmbeddingService {
    private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-embedding-001:embedContent";
    private final String apiKey;
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();
    public EmbeddingService(String apiKey) { this.apiKey = apiKey; }
    public double[] getEmbedding(String text) throws IOException {
        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(Map.of("text", text)));
        Map<String, Object> body = new HashMap<>();
        body.put("content", content);
        String json = gson.toJson(body);
        Request request = new Request.Builder().url(GEMINI_URL + "?key=" + apiKey).post(RequestBody.create(json, MediaType.get("application/json"))).build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Gemini API error: " + response.code() + " " + response.body().string());
            JsonObject jsonObj = JsonParser.parseString(response.body().string()).getAsJsonObject();
            JsonArray values = jsonObj.getAsJsonObject("embedding").getAsJsonArray("values");
            double[] embedding = new double[values.size()];
            for (int i = 0; i < values.size(); i++) embedding[i] = values.get(i).getAsDouble();
            return embedding;
        }
    }
}