package com.sseemitter.sample.controller;

import com.sseemitter.sample.service.SseEmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/v1/sse")
@RequiredArgsConstructor
public class SseEmitterController {
    private final SseEmitterService emitterService;

    @GetMapping("/subscribe/{sessionId}")
    public SseEmitter subscribe(@PathVariable String sessionId) {
        return emitterService.subscribe(sessionId);
    }

    @DeleteMapping("/unsubscribe/{sessionId}")
    public void unsubscribe(@PathVariable String sessionId) {
        emitterService.unsubscribe(sessionId);
    }
}