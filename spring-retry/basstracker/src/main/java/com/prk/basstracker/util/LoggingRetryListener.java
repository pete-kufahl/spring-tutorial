package com.prk.basstracker.util;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
public class LoggingRetryListener implements RetryListener {

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        // Called before first attempt
        System.out.println("🔁 Retry started for: " + context.getAttribute("context.name"));
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        // Called after all retry attempts (whether successful or not)
        System.out.println("✅ Retry finished for: " + context.getAttribute("context.name"));
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        // Called on each retryable exception
        System.out.println("⚠️  Retry attempt " + context.getRetryCount() +
                " failed for: " + context.getAttribute("context.name") +
                " with error: " + throwable.getClass().getSimpleName() + " - " + throwable.getMessage());
    }
}

