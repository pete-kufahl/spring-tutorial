package com.prk.faq_assistant.service;


import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuggingFaceService {

    private final ChatModel chatModel;

    public HuggingFaceService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String userInput) {
        Prompt prompt = new Prompt(List.of(
                new SystemMessage("You are a helpful assistant."),
                new UserMessage(userInput)
        ));

        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().toString();
    }
}
