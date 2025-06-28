package com.prk.faq_assistant.controller;

import com.prk.faq_assistant.service.HuggingFaceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final HuggingFaceService huggingFaceService;

    public ChatController(HuggingFaceService huggingFaceService) {
        this.huggingFaceService = huggingFaceService;
    }

    @GetMapping
    public String chat(@RequestParam String message) {
        return huggingFaceService.getResponse(message);
    }
}
