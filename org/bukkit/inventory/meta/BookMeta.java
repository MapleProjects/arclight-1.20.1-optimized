/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import java.util.List;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BookMeta
extends ItemMeta {
    public boolean hasTitle();

    @Nullable
    public String getTitle();

    public boolean setTitle(@Nullable String var1);

    public boolean hasAuthor();

    @Nullable
    public String getAuthor();

    public void setAuthor(@Nullable String var1);

    public boolean hasGeneration();

    @Nullable
    public Generation getGeneration();

    public void setGeneration(@Nullable Generation var1);

    public boolean hasPages();

    @NotNull
    public String getPage(int var1);

    public void setPage(int var1, @NotNull String var2);

    @NotNull
    public List<String> getPages();

    public void setPages(@NotNull List<String> var1);

    public void setPages(String ... var1);

    public void addPage(String ... var1);

    public int getPageCount();

    @Override
    @NotNull
    public BookMeta clone();

    @NotNull
    public Spigot spigot();

    public static enum Generation {
        ORIGINAL,
        COPY_OF_ORIGINAL,
        COPY_OF_COPY,
        TATTERED;

    }

    public static class Spigot {
        @NotNull
        public BaseComponent[] getPage(int page) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setPage(int page, BaseComponent ... data) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @NotNull
        public List<BaseComponent[]> getPages() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setPages(@NotNull List<BaseComponent[]> pages) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setPages(BaseComponent[] ... pages) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void addPage(BaseComponent[] ... pages) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

