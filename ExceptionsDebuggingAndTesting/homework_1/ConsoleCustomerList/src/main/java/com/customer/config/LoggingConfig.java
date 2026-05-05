package com.customer.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingConfig {
    private static final Logger queriesLogger = LogManager.getLogger("queries");
    private static final Logger errorsLogger = LogManager.getLogger("errors");

    private static volatile boolean initialized = false;

    public static void initialize() {
        if (!initialized) {
            synchronized (LoggingConfig.class) {
                if (!initialized) {
                    // Принудительная инициализация Log4j2
                    ClassLoader classLoader = LoggingConfig.class.getClassLoader();
                    if (classLoader.getResource("log4j2.xml") != null) {
                        System.out.println("Log4j2 конфигурация загружена");
                    } else {
                        System.err.println("log4j2.xml не найден в classpath");
                    }

                    // Проверка работы логгеров
                    queriesLogger.info("=== Логгер запросов инициализирован ===");
                    errorsLogger.info("=== Логгер ошибок инициализирован ===");

                    initialized = true;
                }
            }
        }
    }
}