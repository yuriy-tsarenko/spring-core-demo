package com.go.it.spring.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Properties;

import static com.go.it.spring.util.Constants.DEFAULT_APP_FILE_NAME;
import static com.go.it.spring.util.Constants.LOG_ENCODING;
import static com.go.it.spring.util.Constants.LOG_FILE;
import static com.go.it.spring.util.Constants.LOG_FILE_NAME_PATTERN;
import static com.go.it.spring.util.Constants.LOG_LEVEL;
import static com.go.it.spring.util.Constants.LOG_PATTERN;

public class LoggerConfiguration {

    private static final String APP_BANNER_NAME = "banner.txt";


    public static void setupLogger() {
        try {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            Logger rootLogger = context.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
            rootLogger.detachAndStopAllAppenders();
            Properties properties = loadProps();

            rootLogger.setLevel(Level.valueOf(properties.getProperty(LOG_LEVEL)));
            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setPattern(properties.getProperty(LOG_PATTERN));
            encoder.setCharset(Charset.forName(properties.getProperty(LOG_ENCODING)));
            encoder.setContext(context);
            encoder.start();

            ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
            consoleAppender.setEncoder(encoder);
            consoleAppender.setContext(context);
            consoleAppender.start();
            rootLogger.addAppender(consoleAppender);

            // creates file appender
            Optional<String> fileName = Optional.ofNullable(properties.getProperty(LOG_FILE));

            fileName.ifPresent(logFile -> {
                RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<>();
                rollingFileAppender.setFile(logFile);
                rollingFileAppender.setEncoder(encoder);
                rollingFileAppender.setContext(context);


                TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<>();
                rollingPolicy.setFileNamePattern(properties.getProperty(LOG_FILE_NAME_PATTERN));
                rollingPolicy.setParent(rollingFileAppender);
                rollingPolicy.setContext(context);
                rollingPolicy.start();

                rollingFileAppender.setRollingPolicy(rollingPolicy);
                rollingFileAppender.start();
                rootLogger.addAppender(rollingFileAppender);
            });
            rootLogger.info(loadBanner());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadProps() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(DEFAULT_APP_FILE_NAME));
        return properties;
    }

    private static String loadBanner() throws IOException {
        InputStream resource = ClassLoader.getSystemClassLoader().getResourceAsStream(APP_BANNER_NAME);
        return StreamUtils.copyToString(resource, StandardCharsets.UTF_8);
    }
}
