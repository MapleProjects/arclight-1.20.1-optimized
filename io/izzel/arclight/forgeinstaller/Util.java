/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.forgeinstaller;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

class Util {
    private static final String SHA_PAD = String.format("%040d", 0);

    Util() {
    }

    public static String mavenToPath(String maven) {
        String type;
        if (maven.matches(".*@\\w+$")) {
            int i = maven.lastIndexOf(64);
            type = maven.substring(i + 1);
            maven = maven.substring(0, i);
        } else {
            type = "jar";
        }
        String[] arr = maven.split(":");
        if (arr.length == 3) {
            String pkg = arr[0].replace('.', '/');
            return String.format("%s/%s/%s/%s-%s.%s", pkg, arr[1], arr[2], arr[1], arr[2], type);
        }
        if (arr.length == 4) {
            String pkg = arr[0].replace('.', '/');
            return String.format("%s/%s/%s/%s-%s-%s.%s", pkg, arr[1], arr[2], arr[1], arr[2], arr[3], type);
        }
        throw new RuntimeException("Wrong maven coordinate " + maven);
    }

    public static String hash(String path) throws Exception {
        return Util.hash(new File(path).toPath());
    }

    public static String hash(File path) throws Exception {
        return Util.hash(path.toPath());
    }

    public static String hash(Path path) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        String hash = new BigInteger(1, digest.digest(Files.readAllBytes(path))).toString(16);
        return (SHA_PAD + hash).substring(hash.length());
    }
}

