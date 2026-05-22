package com.zstyle.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String generateOutfitSuggestion(
            String userPrompt,
            List<String> productMeta
    ) {

        StringBuilder fullPrompt = new StringBuilder();

        fullPrompt.append("""
                You are a professional luxury fashion stylist.

                Your job:
                - Analyze given clothing items
                - Suggest stylish outfit combinations
                - Match outfits to the user's occasion
                - Recommend colors and layering
                - Keep response short, premium, modern, and stylish

                User request:
                """);

        fullPrompt.append(userPrompt).append("\n\n");

        fullPrompt.append("Available wardrobe:\n");

        for (String meta : productMeta) {

            fullPrompt
                    .append("- ")
                    .append(meta)
                    .append("\n");
        }

        try {

            JSONObject textPart = new JSONObject();

            textPart.put(
                    "text",
                    fullPrompt.toString()
            );

            JSONArray parts = new JSONArray();

            parts.put(textPart);

            JSONObject content = new JSONObject();

            content.put("parts", parts);

            JSONArray contents = new JSONArray();

            contents.put(content);

            JSONObject requestJson = new JSONObject();

            requestJson.put("contents", contents);

            String requestBody = requestJson.toString();

            // ✅ UPDATED GEMINI MODEL
            String endpoint =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
                            + apiKey;

            java.net.URL url =
                    new java.net.URL(endpoint);

            java.net.HttpURLConnection conn =
                    (java.net.HttpURLConnection)
                            url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty(
                    "Content-Type",
                    "application/json"
            );

            conn.setDoOutput(true);

            // ✅ SEND REQUEST
            try (java.io.OutputStream os =
                         conn.getOutputStream()) {

                os.write(
                        requestBody.getBytes()
                );
            }

            // ✅ SUCCESS / ERROR STREAM
            java.io.InputStream inputStream =
                    (conn.getResponseCode() >= 200
                            && conn.getResponseCode() < 300)
                            ? conn.getInputStream()
                            : conn.getErrorStream();

            java.io.BufferedReader br =
                    new java.io.BufferedReader(
                            new java.io.InputStreamReader(
                                    inputStream
                            )
                    );

            StringBuilder response =
                    new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {

                response.append(line);
            }

            String jsonResponse =
                    response.toString();

            System.out.println(
                    "RAW AI RESPONSE: "
                            + jsonResponse
            );

            JSONObject obj =
                    new JSONObject(jsonResponse);

            // ✅ GEMINI ERROR
            if (obj.has("error")) {

                JSONObject error =
                        obj.getJSONObject("error");

                return "Gemini Error: "
                        + error.getString("message");
            }

            // ✅ CANDIDATES
            if (obj.has("candidates")) {

                JSONArray candidates =
                        obj.getJSONArray("candidates");

                if (candidates.length() > 0) {

                    JSONObject first =
                            candidates.getJSONObject(0);

                    JSONObject contentObj =
                            first.getJSONObject("content");

                    JSONArray responseParts =
                            contentObj.getJSONArray("parts");

                    if (responseParts.length() > 0) {

                        JSONObject firstPart =
                                responseParts.getJSONObject(0);

                        if (firstPart.has("text")) {

                            return firstPart
                                    .getString("text");
                        }
                    }
                }
            }

            return "No response generated.";

        } catch (Exception e) {

            e.printStackTrace();

            return "AI Service Error: "
                    + e.getMessage();
        }
    }
}