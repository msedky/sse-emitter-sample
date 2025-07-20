package com.sseemitter.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseEmitterService {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String sessionId) {
        SseEmitter emitter = new SseEmitter();
        emitters.put(sessionId, emitter);
        emitter.onCompletion(() -> emitters.remove(sessionId));
        emitter.onTimeout(() -> emitters.remove(sessionId));
        return emitter;
    }

    public void unsubscribe(String sessionId) {
        emitters.remove(sessionId);
    }

    public void notifyClients() {
        List<String> deadEmitters = new ArrayList<>();
        for (Map.Entry<String, SseEmitter> entry : emitters.entrySet()) {
            try {
                entry.getValue().send(SseEmitter.event().data("update"));
            } catch (IOException e) {
                deadEmitters.add(entry.getKey());
            }
        }
        deadEmitters.forEach(emitters::remove);
    }
}