package com.github.peckb1.interview.util;


import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class RetryWithDelay implements Function<Flowable<? extends Throwable>, Publisher<?>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryWithDelay.class);

    private final int maxRetries;
    private final int retryDelayMillis;
    private final int backoffExponentialMultiplier;
    private int retryCount;

    public RetryWithDelay(final int maxRetries, final int retryDelayMillis) {
        this(maxRetries, retryDelayMillis, 1);
    }

    public RetryWithDelay(final int maxRetries, final int retryDelayMillis, final int backoffExponentialMultiplier) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.backoffExponentialMultiplier = backoffExponentialMultiplier;
        this.retryCount = 0;
    }

    @Override
    public Publisher<?> apply(@NonNull Flowable<? extends Throwable> flowable) throws Exception {
        return flowable.flatMap(throwable -> {
            if (++retryCount <= maxRetries) {
                LOGGER.warn(String.format("Retry %d due to %s", retryCount, throwable.getMessage()));
                // When this Observable calls onNext, the original
                // Observable will be retried (i.e. re-subscribed).
                long exponentialMultiplier = (long) Math.pow(this.backoffExponentialMultiplier, retryCount - 1);
                return Flowable.timer(retryDelayMillis * exponentialMultiplier, TimeUnit.MILLISECONDS);

            } else {
                LOGGER.error(String.format("Hit the maximum number of Retries[%d] due to %s", maxRetries, throwable.getMessage()), throwable);
                // Max retries hit. Just pass the error along.
                return Flowable.error(throwable);
            }
        });
    }
}