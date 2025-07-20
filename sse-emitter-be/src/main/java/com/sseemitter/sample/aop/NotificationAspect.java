package com.sseemitter.sample.aop;

import com.sseemitter.sample.service.SseEmitterService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class NotificationAspect {

    private final SseEmitterService emitterService;

    @AfterReturning("execution(* com.sseemitter.sample..*.create(..)) || execution(* com.sseemitter.sample..*.delete(..))")
    public void afterSaveOrDelete() {
        emitterService.notifyClients();
    }
}