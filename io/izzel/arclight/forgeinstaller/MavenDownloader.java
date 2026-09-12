/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.forgeinstaller;

import io.izzel.arclight.forgeinstaller.FileDownloader;
import io.izzel.arclight.forgeinstaller.Mirrors;
import io.izzel.arclight.forgeinstaller.Util;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.function.Supplier;

public class MavenDownloader
implements Supplier<Path> {
    private final LinkedList<String> urls = new LinkedList();
    private final String coord;
    private final String target;
    private final String hash;

    public MavenDownloader(String[] repos, String coord, String target, String hash) {
        this.coord = coord;
        this.target = target;
        this.hash = hash;
        String path = Util.mavenToPath(coord);
        for (String repo : repos) {
            this.urls.add(repo + path);
        }
    }

    public MavenDownloader(String[] repos, String coord, String target, String hash, String sourceUrl) {
        this(repos, coord, target, hash);
        if (sourceUrl != null && !this.urls.contains(sourceUrl)) {
            if (Mirrors.isMirrorUrl(sourceUrl)) {
                this.urls.addFirst(sourceUrl);
            } else {
                this.urls.addLast(sourceUrl);
            }
        }
    }

    @Override
    public Path get() {
        ArrayList<Exception> exceptions = new ArrayList<Exception>();
        for (String url : this.urls) {
            try {
                return new FileDownloader(url, this.target, this.hash).get();
            }
            catch (Exception e) {
                exceptions.add(e);
            }
        }
        StringJoiner joiner = new StringJoiner("\n  ");
        joiner.add("");
        for (int i = 0; i < exceptions.size(); ++i) {
            Exception exception = (Exception)exceptions.get(i);
            joiner.add("(" + (i + 1) + ") " + String.valueOf(exception));
        }
        throw new RuntimeException("Failed %s %s".formatted(this.coord, joiner.toString()));
    }
}

