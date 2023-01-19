package soya.framework.util.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class LoggingService {
    private static LoggingService instance;

    protected LoggingService() {
        instance = this;
    }

    public static LoggingService getInstance() {
        if (instance == null) {
            new DefaultLoggingService();
        }
        return instance;
    }

    public abstract void log(Class<?> caller, String message);

    public abstract void log(Class<?> caller, Throwable throwable);

    public abstract void log(Level level, Class<?> call, String message);

    static class DefaultLoggingService extends LoggingService {

        protected DefaultLoggingService() {
            super();
        }

        @Override
        public void log(Class<?> caller, String message) {
            // do nothing
        }

        @Override
        public void log(Class<?> caller, Throwable throwable) {
            // do nothing
        }

        @Override
        public void log(Level level, Class<?> call, String message) {
            // do nothing
        }
    }

    static class JulLoggingService extends LoggingService {
        private boolean enabled = true;
        private Level defaultLevel = Level.INFO;
        private Level defaultErrorLevel = Level.SEVERE;

        protected JulLoggingService() {
        }

        public JulLoggingService(Level defaultLevel, Level defaultErrorLevel) {
            this.enabled = true;
            this.defaultLevel = defaultLevel;
            this.defaultErrorLevel = defaultErrorLevel;
        }

        @Override
        public void log(Class<?> caller, String message) {
            Logger.getLogger(caller.getName()).log(defaultLevel, message);
        }

        @Override
        public void log(Class<?> caller, Throwable throwable) {

        }

        @Override
        public void log(Level level, Class<?> caller, String message) {
            Logger.getLogger(caller.getName()).log(level, message);
        }
    }

}
