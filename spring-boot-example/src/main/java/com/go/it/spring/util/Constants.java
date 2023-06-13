package com.go.it.spring.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    //Common
    public static final String DEFAULT_APP_FILE_NAME = "application.properties";

    //LOGGER
    public static final String LOG_PATTERN = "logback.conversion.pattern";
    public static final String LOG_FILE = "logback.appender.file";
    public static final String LOG_LEVEL = "logback.logger.level";
    public static final String LOG_ENCODING = "logback.logger.encoding";
    public static final String LOG_FILE_NAME_PATTERN = "logback.file.name.pattern";

    //App props
    public static final String CONNECTION_URL = "com.mysql.url";
    public static final String CONNECTION_USERNAME = "com.mysql.username";
    public static final String CONNECTION_PASSWORD = "com.mysql.password";
    public static final String CONNECTION_AUTOCOMMIT = "com.mysql.autocommit";
    public static final String TRANSACTION_ISOLATION = "com.mysql.isolation.level";
    public static final String FLYWAY_CONNECTION_URL = "org.flywaydb.url";
    public static final String FLYWAY_LOGGER = "org.flywaydb.logger";
    public static final String FLYWAY_USER = "org.flywaydb.user";
    public static final String FLYWAY_PASSWORD = "org.flywaydb.password";
    public static final String TOKEN = "servlet.auth.token";
    public static final String USERNAME = "app.username";
    public static final String PASSWORD = "app.password";
    public static final String HOST_PORT = "server.host-port";
    public static final String PROTOCOL = "server.protocol";
    public static final String BASE_PACKAGE = "hibernate.entity.package";

}
