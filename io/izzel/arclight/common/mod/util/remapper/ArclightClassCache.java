/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.hash.Hasher
 *  com.google.common.hash.Hashing
 *  cpw.mods.modlauncher.api.LamdbaExceptionUtils
 *  io.izzel.arclight.api.PluginPatcher
 *  io.izzel.arclight.i18n.ArclightConfig
 *  io.izzel.tools.product.Product
 *  io.izzel.tools.product.Product3
 *  io.izzel.tools.product.Product5
 *  net.minecraftforge.fml.ModContainer
 *  net.minecraftforge.fml.ModList
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 */
package io.izzel.arclight.common.mod.util.remapper;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import io.izzel.arclight.api.PluginPatcher;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapper;
import io.izzel.arclight.i18n.ArclightConfig;
import io.izzel.tools.product.Product;
import io.izzel.tools.product.Product3;
import io.izzel.tools.product.Product5;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.jar.JarFile;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public abstract class ArclightClassCache
implements AutoCloseable {
    private static final Marker MARKER = MarkerManager.getMarker((String)"CLCACHE");
    private static final ArclightClassCache INSTANCE = new Impl();

    public abstract CacheSegment makeSegment(URLConnection var1) throws IOException;

    public abstract void save() throws IOException;

    public static ArclightClassCache instance() {
        return INSTANCE;
    }

    private static class Impl
    extends ArclightClassCache {
        private static final int SPEC_VERSION = 2;
        private final boolean enabled = ArclightConfig.spec().getOptimization().isCachePluginClass();
        private final ConcurrentHashMap<String, JarSegment> map = new ConcurrentHashMap();
        private final Path basePath = Paths.get(".arclight/class_cache", new String[0]);
        private ScheduledExecutorService executor;

        private static String currentVersionInfo() {
            StringBuilder builder = new StringBuilder();
            String arclight = ((ModContainer)ModList.get().getModContainerById("arclight").orElseThrow(IllegalStateException::new)).getModInfo().getVersion().toString();
            builder.append(arclight);
            builder.append("Arclight class cache").append(", ");
            builder.append("spec=").append(2).append(", ");
            builder.append("arclight=").append(arclight).append(", ");
            builder.append("patcher=[");
            for (PluginPatcher patcher : ArclightRemapper.INSTANCE.getPatchers()) {
                builder.append('\u0000').append(patcher.getClass().getName()).append('\u0000').append(patcher.version()).append(", ");
            }
            builder.append("]");
            return builder.toString();
        }

        public Impl() {
            if (!this.enabled) {
                return;
            }
            this.executor = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread thread = new Thread(r);
                thread.setName("arclight class cache saving thread");
                thread.setDaemon(true);
                return thread;
            });
            this.executor.scheduleWithFixedDelay(() -> {
                try {
                    this.save();
                }
                catch (IOException e) {
                    ArclightMod.LOGGER.error(MARKER, "Failed to save class cache", (Throwable)e);
                }
            }, 1L, 10L, TimeUnit.MINUTES);
            try {
                if (Files.isRegularFile(this.basePath, new LinkOption[0])) {
                    Files.delete(this.basePath);
                }
                if (!Files.isDirectory(this.basePath, new LinkOption[0])) {
                    Files.createDirectories(this.basePath, new FileAttribute[0]);
                }
                String current = Impl.currentVersionInfo();
                Path version = this.basePath.resolve(".version");
                String store = Files.exists(version, new LinkOption[0]) ? Files.readString(version) : null;
                boolean obsolete = !Objects.equals(current, store);
                Path index = this.basePath.resolve("index");
                if (obsolete) {
                    FileUtils.deleteDirectory((File)index.toFile());
                }
                if (!Files.exists(index, new LinkOption[0])) {
                    Files.createDirectories(index, new FileAttribute[0]);
                }
                Path blob = this.basePath.resolve("blob");
                if (obsolete) {
                    FileUtils.deleteDirectory((File)blob.toFile());
                }
                if (!Files.exists(blob, new LinkOption[0])) {
                    Files.createDirectories(blob, new FileAttribute[0]);
                }
                if (obsolete) {
                    Files.deleteIfExists(version);
                    Files.writeString(version, (CharSequence)current, StandardOpenOption.CREATE);
                    ArclightMod.LOGGER.info(MARKER, "Obsolete plugin class cache is cleared");
                }
            }
            catch (IOException e) {
                ArclightMod.LOGGER.error(MARKER, "Failed to initialize class cache", (Throwable)e);
            }
            Thread thread = new Thread(() -> {
                try {
                    this.close();
                }
                catch (Exception e) {
                    ArclightMod.LOGGER.error(MARKER, "Failed to close class cache", (Throwable)e);
                }
            }, "arclight class cache cleanup");
            thread.setDaemon(true);
            Runtime.getRuntime().addShutdownHook(thread);
        }

        @Override
        public CacheSegment makeSegment(URLConnection connection) throws IOException {
            if (this.enabled && connection instanceof JarURLConnection) {
                JarFile file = ((JarURLConnection)connection).getJarFile();
                return this.map.computeIfAbsent(file.getName(), LamdbaExceptionUtils.rethrowFunction(x$0 -> new JarSegment((String)x$0)));
            }
            return new EmptySegment();
        }

        @Override
        public void save() throws IOException {
            if (this.enabled) {
                for (CacheSegment cacheSegment : this.map.values()) {
                    cacheSegment.save();
                }
            }
        }

        @Override
        public void close() throws Exception {
            if (this.enabled) {
                this.save();
                this.executor.shutdownNow();
            }
        }

        private static class EmptySegment
        implements CacheSegment {
            private EmptySegment() {
            }

            @Override
            public Optional<byte[]> findByName(String name, ArclightRemapConfig config) {
                return Optional.empty();
            }

            @Override
            public void addToCache(String name, byte[] value, ArclightRemapConfig config) {
            }

            @Override
            public void save() {
            }
        }

        private class JarSegment
        implements CacheSegment {
            private final Map<String, Product3<Long, Integer, ArclightRemapConfig>> rangeMap = new ConcurrentHashMap<String, Product3<Long, Integer, ArclightRemapConfig>>();
            private final ConcurrentLinkedQueue<Product5<String, byte[], Long, Integer, ArclightRemapConfig>> savingQueue = new ConcurrentLinkedQueue();
            private final AtomicLong sizeAllocator;
            private final Path indexPath;
            private final Path blobPath;

            private JarSegment(String fileName) throws IOException {
                Path jarFile = new File(fileName).toPath();
                Hasher hasher = Hashing.sha256().newHasher();
                hasher.putBytes(Files.readAllBytes(jarFile));
                String hash = hasher.hash().toString();
                this.indexPath = Impl.this.basePath.resolve("index").resolve(hash);
                this.blobPath = Impl.this.basePath.resolve("blob").resolve(hash);
                if (!Files.exists(this.indexPath, new LinkOption[0])) {
                    Files.createFile(this.indexPath, new FileAttribute[0]);
                }
                if (!Files.exists(this.blobPath, new LinkOption[0])) {
                    Files.createFile(this.blobPath, new FileAttribute[0]);
                }
                this.sizeAllocator = new AtomicLong(Files.size(this.blobPath));
                this.read();
            }

            @Override
            public Optional<byte[]> findByName(String name, ArclightRemapConfig config) throws IOException {
                Product3<Long, Integer, ArclightRemapConfig> product = this.rangeMap.get(name);
                if (product != null) {
                    long off = (Long)product._1;
                    int len = (Integer)product._2;
                    ArclightRemapConfig cfg = (ArclightRemapConfig)product._3;
                    if (!cfg.equals(config)) {
                        return Optional.empty();
                    }
                    try (SeekableByteChannel channel = Files.newByteChannel(this.blobPath, new OpenOption[0]);){
                        channel.position(off);
                        ByteBuffer buffer = ByteBuffer.allocate(len);
                        channel.read(buffer);
                        Optional<byte[]> optional = Optional.of(buffer.array());
                        return optional;
                    }
                }
                return Optional.empty();
            }

            @Override
            public void addToCache(String name, byte[] value, ArclightRemapConfig config) {
                int len = value.length;
                long off = this.sizeAllocator.getAndAdd(len);
                this.savingQueue.add((Product5<String, byte[], Long, Integer, ArclightRemapConfig>)Product.of((Object)name, (Object)value, (Object)off, (Object)len, (Object)config.copy()));
            }

            @Override
            public synchronized void save() throws IOException {
                if (this.savingQueue.isEmpty()) {
                    return;
                }
                ArrayList<Product5<String, byte[], Long, Integer, ArclightRemapConfig>> list = new ArrayList<Product5<String, byte[], Long, Integer, ArclightRemapConfig>>();
                while (!this.savingQueue.isEmpty()) {
                    list.add(this.savingQueue.poll());
                }
                try (OutputStream outIndex = Files.newOutputStream(this.indexPath, StandardOpenOption.APPEND);
                     DataOutputStream dataOutIndex = new DataOutputStream(outIndex);
                     SeekableByteChannel channel = Files.newByteChannel(this.blobPath, StandardOpenOption.WRITE);){
                    for (Product5 product5 : list) {
                        channel.position((Long)product5._3);
                        channel.write(ByteBuffer.wrap((byte[])product5._2));
                        dataOutIndex.writeUTF((String)product5._1);
                        dataOutIndex.writeLong((Long)product5._3);
                        dataOutIndex.writeInt((Integer)product5._4);
                        ((ArclightRemapConfig)product5._5).write(dataOutIndex);
                        this.rangeMap.put((String)product5._1, (Product3<Long, Integer, ArclightRemapConfig>)Product.of((Object)((Long)product5._3), (Object)((Integer)product5._4), (Object)((ArclightRemapConfig)product5._5)));
                    }
                }
            }

            private synchronized void read() throws IOException {
                try (InputStream inputStream = Files.newInputStream(this.indexPath, new OpenOption[0]);
                     DataInputStream dataIn = new DataInputStream(inputStream);){
                    while (dataIn.available() > 0) {
                        String name = dataIn.readUTF();
                        long off = dataIn.readLong();
                        int len = dataIn.readInt();
                        ArclightRemapConfig cfg = ArclightRemapConfig.read(dataIn);
                        this.rangeMap.put(name, (Product3<Long, Integer, ArclightRemapConfig>)Product.of((Object)off, (Object)len, (Object)cfg));
                    }
                }
            }
        }
    }

    public static interface CacheSegment {
        public Optional<byte[]> findByName(String var1, ArclightRemapConfig var2) throws IOException;

        public void addToCache(String var1, byte[] var2, ArclightRemapConfig var3);

        public void save() throws IOException;
    }
}

