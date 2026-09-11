/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.Util
 */
package org.bukkit.craftbukkit.v1_20_R1.map;

import com.google.common.base.Preconditions;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import net.minecraft.Util;
import org.bukkit.map.MapPalette;

public class CraftMapColorCache
implements MapPalette.MapColorCache {
    private static final String MD5_CACHE_HASH = "E88EDD068D12D39934B40E8B6B124C83";
    private static final File CACHE_FILE = new File("map-color-cache.dat");
    private byte[] cache;
    private final Logger logger;
    private boolean cached = false;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public CraftMapColorCache(Logger logger) {
        this.logger = logger;
    }

    public static void main(String[] args) {
        CraftMapColorCache craftMapColorCache = new CraftMapColorCache(Logger.getGlobal());
        craftMapColorCache.buildCache();
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(craftMapColorCache.cache);
            System.out.println("MD5_CACHE_HASH: " + CraftMapColorCache.bytesToString(hash));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String bytesToString(byte[] bytes) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder builder = new StringBuilder();
        byte[] byArray = bytes;
        int n = bytes.length;
        int n2 = 0;
        while (n2 < n) {
            byte value = byArray[n2];
            int first = (value & 0xF0) >> 4;
            int second = value & 0xF;
            builder.append(chars[first]);
            builder.append(chars[second]);
            ++n2;
        }
        return builder.toString();
    }

    public CompletableFuture<Void> initCache() {
        byte[] fileContent;
        Preconditions.checkState((!this.cached && !this.running.getAndSet(true) ? 1 : 0) != 0, (Object)"Cache is already build or is currently being build");
        this.cache = new byte[0x1000000];
        if (CACHE_FILE.exists()) {
            byte[] hash;
            try {
                Throwable throwable = null;
                Object var3_4 = null;
                try (InflaterInputStream inputStream = new InflaterInputStream(new FileInputStream(CACHE_FILE));){
                    fileContent = inputStream.readAllBytes();
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
            }
            catch (IOException e) {
                this.logger.warning("Error while reading map color cache");
                e.printStackTrace();
                return CompletableFuture.completedFuture(null);
            }
            try {
                hash = MessageDigest.getInstance("MD5").digest(fileContent);
            }
            catch (NoSuchAlgorithmException e) {
                this.logger.warning("Error while hashing map color cache");
                e.printStackTrace();
                return CompletableFuture.completedFuture(null);
            }
            if (!MD5_CACHE_HASH.equals(CraftMapColorCache.bytesToString(hash))) {
                this.logger.info("Map color cache hash invalid, rebuilding cache in the background");
                return this.buildAndSaveCache();
            }
        } else {
            this.logger.info("Map color cache not found, building it in the background");
            return this.buildAndSaveCache();
        }
        System.arraycopy(fileContent, 0, this.cache, 0, fileContent.length);
        this.cached = true;
        return CompletableFuture.completedFuture(null);
    }

    private void buildCache() {
        int r = 0;
        while (r < 256) {
            int g = 0;
            while (g < 256) {
                int b = 0;
                while (b < 256) {
                    Color color = new Color(r, g, b);
                    this.cache[this.toInt((Color)color)] = MapPalette.matchColor(color);
                    ++b;
                }
                ++g;
            }
            ++r;
        }
    }

    private CompletableFuture<Void> buildAndSaveCache() {
        return CompletableFuture.runAsync(() -> {
            this.buildCache();
            if (!CACHE_FILE.exists()) {
                try {
                    if (!CACHE_FILE.createNewFile()) {
                        this.cached = true;
                        return;
                    }
                }
                catch (IOException e) {
                    this.logger.warning("Error while building map color cache");
                    e.printStackTrace();
                    this.cached = true;
                    return;
                }
            }
            try {
                Throwable e = null;
                Object var2_5 = null;
                try (DeflaterOutputStream outputStream = new DeflaterOutputStream(new FileOutputStream(CACHE_FILE));){
                    ((OutputStream)outputStream).write(this.cache);
                }
                catch (Throwable throwable) {
                    if (e == null) {
                        e = throwable;
                    } else if (e != throwable) {
                        e.addSuppressed(throwable);
                    }
                    throw e;
                }
            }
            catch (IOException e) {
                this.logger.warning("Error while building map color cache");
                e.printStackTrace();
                this.cached = true;
                return;
            }
            this.cached = true;
            this.logger.info("Map color cache build successfully");
        }, Util.m_183991_());
    }

    private int toInt(Color color) {
        return color.getRGB() & 0xFFFFFF;
    }

    @Override
    public boolean isCached() {
        return this.cached || !this.running.get() && this.initCache().isDone();
    }

    @Override
    public byte matchColor(Color color) {
        Preconditions.checkState((boolean)this.isCached(), (Object)"Cache not build jet");
        return this.cache[this.toInt(color)];
    }
}

