/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.server;

import io.izzel.arclight.boot.application.Main_Forge;

public class Launcher {
    private static final int MIN_CLASS_VERSION = 61;
    private static final int MIN_JAVA_VERSION = 17;

    public static void main(String[] args) throws Throwable {
        int javaVersion = (int)Float.parseFloat(System.getProperty("java.class.version"));
        if (javaVersion < 61) {
            System.err.println("Arclight requires Java 17");
            System.err.println("Current: " + System.getProperty("java.version"));
            System.exit(-1);
            return;
        }
        Main_Forge.main(args);
    }
}

