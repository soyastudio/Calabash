package soya.framework.util;

import soya.framework.util.logging.LoggingService;

import java.util.logging.Level;

public final class LogUtils {
    private static LoggingService loggingService;

    static {
        loggingService = LoggingService.getInstance();
    }

    private LogUtils() {
    }

    public static void log(Class<?> caller, String message) {
        loggingService.log(caller, message);
    }

    public static void log(Class<?> caller, Throwable e) {
        loggingService.log(caller, e);
    }

    public static void log(Level level, Class<?> caller, String message) {
        loggingService.log(level, caller, message);
    }
}
