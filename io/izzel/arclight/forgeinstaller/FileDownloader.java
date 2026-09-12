/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.forgeinstaller;

import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.forgeinstaller.Util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Supplier;
import javax.net.ssl.SSLException;

public record FileDownloader(String url, String target, String hash) implements Supplier<Path>
{
    @Override
    public Path get() {
        try {
            Path path = new File(this.target).toPath();
            if (Files.exists(path, new LinkOption[0]) && Files.isDirectory(path, new LinkOption[0])) {
                Files.delete(path);
            }
            if (Files.exists(path, new LinkOption[0])) {
                if (Files.isDirectory(path, new LinkOption[0])) {
                    throw new FileAlreadyExistsException(this.target);
                }
                if (Util.hash(path).equalsIgnoreCase(this.hash)) {
                    return path;
                }
                Files.delete(path);
            }
            if (!Files.exists(path, new LinkOption[0]) && path.getParent() != null) {
                Files.createDirectories(path.getParent(), new FileAttribute[0]);
            }
            Path tmp = new File(this.target + ".tmp").toPath();
            try (InputStream stream = FileDownloader.read(this.url);){
                Files.copy(stream, tmp, StandardCopyOption.REPLACE_EXISTING);
            }
            catch (SocketTimeoutException | SSLException e) {
                throw new RuntimeException("Timeout " + this.url);
            }
            if (Files.exists(tmp, new LinkOption[0])) {
                String hash = Util.hash(tmp);
                if (hash.equalsIgnoreCase(this.hash)) {
                    Files.move(tmp, path, StandardCopyOption.REPLACE_EXISTING);
                    return path;
                }
                Files.delete(tmp);
                throw new RuntimeException("Hash not match, expect %s found %s in %s".formatted(this.hash, hash, this.url));
            }
            throw new RuntimeException("Not found " + this.url);
        }
        catch (AccessDeniedException e) {
            throw new RuntimeException("Access denied for file " + e.getFile(), e);
        }
        catch (Exception e) {
            Unsafe.throwException(e);
            return null;
        }
    }

    static InputStream read(String url) throws IOException {
        return FileDownloader.redirect(new URL(url));
    }

    private static InputStream redirect(URL url) throws IOException {
        return FileDownloader.redirect(url, new HashSet<String>());
    }

    private static InputStream redirect(URL url, Set<String> history) throws IOException {
        if (history.contains(url.toString())) {
            StringJoiner joiner = new StringJoiner("\n        ");
            joiner.add("");
            history.forEach(joiner::add);
            throw new RuntimeException("Redirect error " + String.valueOf(joiner));
        }
        history.add(url.toString());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setReadTimeout(15000);
        connection.setConnectTimeout(15000);
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            return connection.getInputStream();
        }
        if (responseCode == 301 || responseCode == 302) {
            String location = URLDecoder.decode(connection.getHeaderField("Location"), StandardCharsets.UTF_8);
            return FileDownloader.redirect(new URL(url, location));
        }
        if (responseCode == 404 || responseCode == 403) {
            throw new RuntimeException("Not found " + String.valueOf(url));
        }
        throw new RemoteException("Http " + responseCode + " " + String.valueOf(url));
    }
}

