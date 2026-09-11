/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.jul.LogManager
 */
package io.izzel.arclight.common.mod.util.log;

import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;

public class ArclightPluginLogger
extends PluginLogger {
    private static final org.apache.logging.log4j.jul.LogManager JUL_MANAGER;
    private final Logger logger;

    public ArclightPluginLogger(Plugin context) {
        super(context);
        String prefix = context.getDescription().getPrefix();
        this.logger = JUL_MANAGER.getLogger(prefix == null ? context.getName() : prefix);
    }

    @Override
    public void log(LogRecord logRecord) {
        this.logger.log(logRecord);
    }

    public static Logger getLogger(String name) {
        return JUL_MANAGER.getLogger(name);
    }

    public static Logger getLogger(String name, String rb) {
        return JUL_MANAGER.getLogger(name);
    }

    static {
        org.apache.logging.log4j.jul.LogManager instance;
        LogManager logManager = LogManager.getLogManager();
        JUL_MANAGER = logManager instanceof org.apache.logging.log4j.jul.LogManager ? (instance = (org.apache.logging.log4j.jul.LogManager)logManager) : new org.apache.logging.log4j.jul.LogManager();
    }
}

