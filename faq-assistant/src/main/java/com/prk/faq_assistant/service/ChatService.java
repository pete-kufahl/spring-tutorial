package com.prk.faq_assistant.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.client.ChatClientRequest;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String getReply(String input) {
        // Build the request
        ChatRequest request = new ChatRequest(new UserMessage(input));

        // Get the response
        ChatResponse response = chatClient.call(request);

        // Return the message content
        return response.getResult().getOutput().getContent();
    }
}
