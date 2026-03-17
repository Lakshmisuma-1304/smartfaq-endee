# SmartFAQ - Semantic FAQ Chatbot using Endee Vector Database

## Project Overview
SmartFAQ is a semantic FAQ chatbot that uses vector search to match user questions to the most relevant FAQ answers — even when the exact words don't match.

## How it Works
- User types a question
- The question is converted to a vector embedding using a sentence transformer model
- Endee vector database finds the most semantically similar FAQ
- The correct answer is returned with a confidence score

## System Design
User Question → EmbeddingService → Vector → EndeeService (Endee DB) → Best Match FAQ → Answer

## Use of Endee
Endee is used as the vector database to store and search FAQ embeddings using cosine similarity for semantic search.

## Tech Stack
- Java 11
- Endee Vector Database
- Maven
- Sentence Transformers (via API)

## Setup Instructions
1. Clone the repository
2. Run `mvn clean package`
3. Run `java -jar target/smartfaq-endee-1.0-SNAPSHOT.jar`

## Mandatory Steps Completed
- Starred Endee repo ✅
- Forked Endee repo ✅
