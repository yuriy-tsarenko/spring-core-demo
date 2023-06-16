package com.go.it.spring.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;

import java.util.Optional;

@RequiredArgsConstructor(staticName = "of")
public final class EnvironmentUtils {

    private final Environment environment;

    public Optional<String> getOptionalProperty(String propKey) {
        return Optional.ofNullable(environment.getProperty(propKey));
    }

    public<T> Optional<T> getOptionalProperty(String propKey, Class<T> type) {
        return Optional.ofNullable(environment.getProperty(propKey, type));
    }
}
