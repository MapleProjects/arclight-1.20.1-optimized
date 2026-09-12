/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.jul.ApiLogger
 *  org.apache.logging.log4j.jul.CoreLoggerAdapter
 *  org.apache.logging.log4j.spi.LoggerContext
 */
package io.izzel.arclight.boot.log;

import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.apache.logging.log4j.jul.ApiLogger;
import org.apache.logging.log4j.jul.CoreLoggerAdapter;
import org.apache.logging.log4j.spi.LoggerContext;

public class ArclightLoggerAdapter
extends CoreLoggerAdapter {
    protected Logger newLogger(String name, LoggerContext context) {
        Logger logger = super.newLogger(name, context);
        if (logger instanceof ApiLogger) {
            return new ArclightJulLogger(logger);
        }
        return logger;
    }

    public static class ArclightJulLogger
    extends Logger {
        private final Logger logger;

        protected ArclightJulLogger(Logger logger) {
            super(logger.getName(), null);
            this.logger = logger;
        }

        public static Logger getLogger(String name) {
            return Logger.getLogger(name);
        }

        public static Logger getLogger(String name, String resourceBundleName) {
            return Logger.getLogger(name, resourceBundleName);
        }

        public static Logger getAnonymousLogger() {
            return Logger.getAnonymousLogger();
        }

        public static Logger getAnonymousLogger(String resourceBundleName) {
            return Logger.getAnonymousLogger(resourceBundleName);
        }

        @Override
        public ResourceBundle getResourceBundle() {
            return this.logger.getResourceBundle();
        }

        @Override
        public String getResourceBundleName() {
            return this.logger.getResourceBundleName();
        }

        @Override
        public void setFilter(Filter newFilter) throws SecurityException {
            this.logger.setFilter(newFilter);
        }

        @Override
        public Filter getFilter() {
            return this.logger.getFilter();
        }

        @Override
        public void log(LogRecord record) {
            this.logger.log(record);
        }

        @Override
        public void log(Level level, String msg) {
            this.logger.log(level, msg);
        }

        @Override
        public void log(Level level, Supplier<String> msgSupplier) {
            this.logger.log(level, msgSupplier);
        }

        @Override
        public void log(Level level, String msg, Object param1) {
            this.logger.log(level, msg, param1);
        }

        @Override
        public void log(Level level, String msg, Object[] params) {
            this.logger.log(level, msg, params);
        }

        @Override
        public void log(Level level, String msg, Throwable thrown) {
            this.logger.log(level, msg, thrown);
        }

        @Override
        public void log(Level level, Throwable thrown, Supplier<String> msgSupplier) {
            this.logger.log(level, thrown, msgSupplier);
        }

        @Override
        public void logp(Level level, String sourceClass, String sourceMethod, String msg) {
            this.logger.logp(level, sourceClass, sourceMethod, msg);
        }

        @Override
        public void logp(Level level, String sourceClass, String sourceMethod, Supplier<String> msgSupplier) {
            this.logger.logp(level, sourceClass, sourceMethod, msgSupplier);
        }

        @Override
        public void logp(Level level, String sourceClass, String sourceMethod, String msg, Object param1) {
            this.logger.logp(level, sourceClass, sourceMethod, msg, param1);
        }

        @Override
        public void logp(Level level, String sourceClass, String sourceMethod, String msg, Object[] params) {
            this.logger.logp(level, sourceClass, sourceMethod, msg, params);
        }

        @Override
        public void logp(Level level, String sourceClass, String sourceMethod, String msg, Throwable thrown) {
            this.logger.logp(level, sourceClass, sourceMethod, msg, thrown);
        }

        @Override
        public void logp(Level level, String sourceClass, String sourceMethod, Throwable thrown, Supplier<String> msgSupplier) {
            this.logger.logp(level, sourceClass, sourceMethod, thrown, msgSupplier);
        }

        @Override
        @Deprecated
        public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg) {
            this.logger.logrb(level, sourceClass, sourceMethod, bundleName, msg);
        }

        @Override
        @Deprecated
        public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg, Object param1) {
            this.logger.logrb(level, sourceClass, sourceMethod, bundleName, msg, param1);
        }

        @Override
        @Deprecated
        public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg, Object[] params) {
            this.logger.logrb(level, sourceClass, sourceMethod, bundleName, msg, params);
        }

        @Override
        public void logrb(Level level, String sourceClass, String sourceMethod, ResourceBundle bundle, String msg, Object ... params) {
            this.logger.logrb(level, sourceClass, sourceMethod, bundle, msg, params);
        }

        @Override
        @Deprecated
        public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg, Throwable thrown) {
            this.logger.logrb(level, sourceClass, sourceMethod, bundleName, msg, thrown);
        }

        @Override
        public void logrb(Level level, String sourceClass, String sourceMethod, ResourceBundle bundle, String msg, Throwable thrown) {
            this.logger.logrb(level, sourceClass, sourceMethod, bundle, msg, thrown);
        }

        @Override
        public void entering(String sourceClass, String sourceMethod) {
            this.logger.entering(sourceClass, sourceMethod);
        }

        @Override
        public void entering(String sourceClass, String sourceMethod, Object param1) {
            this.logger.entering(sourceClass, sourceMethod, param1);
        }

        @Override
        public void entering(String sourceClass, String sourceMethod, Object[] params) {
            this.logger.entering(sourceClass, sourceMethod, params);
        }

        @Override
        public void exiting(String sourceClass, String sourceMethod) {
            this.logger.exiting(sourceClass, sourceMethod);
        }

        @Override
        public void exiting(String sourceClass, String sourceMethod, Object result) {
            this.logger.exiting(sourceClass, sourceMethod, result);
        }

        @Override
        public void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
            this.logger.throwing(sourceClass, sourceMethod, thrown);
        }

        @Override
        public void severe(String msg) {
            this.logger.severe(msg);
        }

        @Override
        public void warning(String msg) {
            this.logger.warning(msg);
        }

        @Override
        public void info(String msg) {
            this.logger.info(msg);
        }

        @Override
        public void config(String msg) {
            this.logger.config(msg);
        }

        @Override
        public void fine(String msg) {
            this.logger.fine(msg);
        }

        @Override
        public void finer(String msg) {
            this.logger.finer(msg);
        }

        @Override
        public void finest(String msg) {
            this.logger.finest(msg);
        }

        @Override
        public void severe(Supplier<String> msgSupplier) {
            this.logger.severe(msgSupplier);
        }

        @Override
        public void warning(Supplier<String> msgSupplier) {
            this.logger.warning(msgSupplier);
        }

        @Override
        public void info(Supplier<String> msgSupplier) {
            this.logger.info(msgSupplier);
        }

        @Override
        public void config(Supplier<String> msgSupplier) {
            this.logger.config(msgSupplier);
        }

        @Override
        public void fine(Supplier<String> msgSupplier) {
            this.logger.fine(msgSupplier);
        }

        @Override
        public void finer(Supplier<String> msgSupplier) {
            this.logger.finer(msgSupplier);
        }

        @Override
        public void finest(Supplier<String> msgSupplier) {
            this.logger.finest(msgSupplier);
        }

        @Override
        public void setLevel(Level newLevel) throws SecurityException {
            try {
                this.logger.setLevel(newLevel);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }

        @Override
        public Level getLevel() {
            return this.logger.getLevel();
        }

        @Override
        public boolean isLoggable(Level level) {
            return this.logger.isLoggable(level);
        }

        @Override
        public String getName() {
            return this.logger.getName();
        }

        @Override
        public void addHandler(Handler handler) throws SecurityException {
            this.logger.addHandler(handler);
        }

        @Override
        public void removeHandler(Handler handler) throws SecurityException {
            this.logger.removeHandler(handler);
        }

        @Override
        public Handler[] getHandlers() {
            return this.logger.getHandlers();
        }

        @Override
        public void setUseParentHandlers(boolean useParentHandlers) {
            this.logger.setUseParentHandlers(useParentHandlers);
        }

        @Override
        public boolean getUseParentHandlers() {
            return this.logger.getUseParentHandlers();
        }

        @Override
        public void setResourceBundle(ResourceBundle bundle) {
            this.logger.setResourceBundle(bundle);
        }

        @Override
        public Logger getParent() {
            return this.logger.getParent();
        }

        @Override
        public void setParent(Logger parent) {
            try {
                this.logger.setParent(parent);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }
}

