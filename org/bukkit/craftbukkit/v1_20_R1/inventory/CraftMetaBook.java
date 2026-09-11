/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Lists
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBookSigned;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.inventory.meta.BookMeta;
import org.spigotmc.ValidateUtils;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaBook
extends CraftMetaItem
implements BookMeta {
    static final CraftMetaItem.ItemMetaKey BOOK_TITLE = new CraftMetaItem.ItemMetaKey("title");
    static final CraftMetaItem.ItemMetaKey BOOK_AUTHOR = new CraftMetaItem.ItemMetaKey("author");
    static final CraftMetaItem.ItemMetaKey BOOK_PAGES = new CraftMetaItem.ItemMetaKey("pages");
    static final CraftMetaItem.ItemMetaKey RESOLVED = new CraftMetaItem.ItemMetaKey("resolved");
    static final CraftMetaItem.ItemMetaKey GENERATION = new CraftMetaItem.ItemMetaKey("generation");
    static final int MAX_PAGES = 100;
    static final int MAX_PAGE_LENGTH = 320;
    static final int MAX_TITLE_LENGTH = 32;
    protected String title;
    protected String author;
    protected List<String> pages;
    protected Boolean resolved = null;
    protected Integer generation;
    private BookMeta.Spigot spigot = new SpigotMeta();

    public CraftMetaBook(CraftMetaItem meta) {
        super(meta);
        if (meta instanceof CraftMetaBook) {
            CraftMetaBook bookMeta = (CraftMetaBook)meta;
            this.title = bookMeta.title;
            this.author = bookMeta.author;
            this.resolved = bookMeta.resolved;
            this.generation = bookMeta.generation;
            if (bookMeta.pages != null) {
                this.pages = new ArrayList<String>(bookMeta.pages.size());
                if (meta instanceof CraftMetaBookSigned) {
                    if (this instanceof CraftMetaBookSigned) {
                        this.pages.addAll(bookMeta.pages);
                    } else {
                        this.pages.addAll(Lists.transform(bookMeta.pages, CraftChatMessage::fromJSONComponent));
                    }
                } else if (this instanceof CraftMetaBookSigned) {
                    for (String page : bookMeta.pages) {
                        Component component = CraftChatMessage.fromString(page, true, true)[0];
                        this.pages.add(CraftChatMessage.toJSON(component));
                    }
                } else {
                    this.pages.addAll(bookMeta.pages);
                }
            }
        }
    }

    CraftMetaBook(CompoundTag tag) {
        super(tag);
        if (tag.m_128441_(CraftMetaBook.BOOK_TITLE.NBT)) {
            this.title = ValidateUtils.limit(tag.m_128461_(CraftMetaBook.BOOK_TITLE.NBT), 8192);
        }
        if (tag.m_128441_(CraftMetaBook.BOOK_AUTHOR.NBT)) {
            this.author = ValidateUtils.limit(tag.m_128461_(CraftMetaBook.BOOK_AUTHOR.NBT), 8192);
        }
        if (tag.m_128441_(CraftMetaBook.RESOLVED.NBT)) {
            this.resolved = tag.m_128471_(CraftMetaBook.RESOLVED.NBT);
        }
        if (tag.m_128441_(CraftMetaBook.GENERATION.NBT)) {
            this.generation = tag.m_128451_(CraftMetaBook.GENERATION.NBT);
        }
        if (tag.m_128441_(CraftMetaBook.BOOK_PAGES.NBT)) {
            ListTag pages = tag.m_128437_(CraftMetaBook.BOOK_PAGES.NBT, 8);
            this.pages = new ArrayList<String>(pages.size());
            boolean expectJson = this instanceof CraftMetaBookSigned;
            int i = 0;
            while (i < Math.min(pages.size(), 100)) {
                String page = pages.m_128778_(i);
                page = expectJson ? CraftChatMessage.fromJSONOrStringToJSON(page, false, true, 320, false) : this.validatePage(page);
                this.pages.add(ValidateUtils.limit(page, 16384));
                ++i;
            }
        }
    }

    CraftMetaBook(Map<String, Object> map) {
        super(map);
        this.setAuthor(CraftMetaItem.SerializableMeta.getString(map, CraftMetaBook.BOOK_AUTHOR.BUKKIT, true));
        this.setTitle(CraftMetaItem.SerializableMeta.getString(map, CraftMetaBook.BOOK_TITLE.BUKKIT, true));
        Iterable pages = CraftMetaItem.SerializableMeta.getObject(Iterable.class, map, CraftMetaBook.BOOK_PAGES.BUKKIT, true);
        if (pages != null) {
            this.pages = new ArrayList<String>();
            for (Object page : pages) {
                if (!(page instanceof String)) continue;
                this.internalAddPage(this.deserializePage((String)page));
            }
        }
        this.resolved = CraftMetaItem.SerializableMeta.getObject(Boolean.class, map, CraftMetaBook.RESOLVED.BUKKIT, true);
        this.generation = CraftMetaItem.SerializableMeta.getObject(Integer.class, map, CraftMetaBook.GENERATION.BUKKIT, true);
    }

    protected String deserializePage(String pageData) {
        return this.validatePage(pageData);
    }

    protected String convertPlainPageToData(String page) {
        return page;
    }

    protected String convertDataToPlainPage(String pageData) {
        return pageData;
    }

    @Override
    void applyToItem(CompoundTag itemData) {
        super.applyToItem(itemData);
        if (this.hasTitle()) {
            itemData.m_128359_(CraftMetaBook.BOOK_TITLE.NBT, this.title);
        }
        if (this.hasAuthor()) {
            itemData.m_128359_(CraftMetaBook.BOOK_AUTHOR.NBT, this.author);
        }
        if (this.pages != null) {
            ListTag list = new ListTag();
            for (String page : this.pages) {
                list.add((Object)StringTag.m_129297_((String)page));
            }
            itemData.m_128365_(CraftMetaBook.BOOK_PAGES.NBT, (Tag)list);
        }
        if (this.resolved != null) {
            itemData.m_128379_(CraftMetaBook.RESOLVED.NBT, this.resolved.booleanValue());
        }
        if (this.generation != null) {
            itemData.m_128405_(CraftMetaBook.GENERATION.NBT, this.generation.intValue());
        }
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isBookEmpty();
    }

    boolean isBookEmpty() {
        return this.pages == null && !this.hasAuthor() && !this.hasTitle() && !this.hasGeneration() && this.resolved == null;
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.WRITTEN_BOOK || type == Material.WRITABLE_BOOK;
    }

    @Override
    public boolean hasAuthor() {
        return this.author != null;
    }

    @Override
    public boolean hasTitle() {
        return this.title != null;
    }

    @Override
    public boolean hasPages() {
        return this.pages != null && !this.pages.isEmpty();
    }

    @Override
    public boolean hasGeneration() {
        return this.generation != null;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public boolean setTitle(String title) {
        if (title == null) {
            this.title = null;
            return true;
        }
        if (title.length() > 32) {
            return false;
        }
        this.title = title;
        return true;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public BookMeta.Generation getGeneration() {
        return this.generation == null ? null : BookMeta.Generation.values()[this.generation];
    }

    @Override
    public void setGeneration(BookMeta.Generation generation) {
        this.generation = generation == null ? null : Integer.valueOf(generation.ordinal());
    }

    @Override
    public String getPage(int page) {
        Preconditions.checkArgument((boolean)this.isValidPage(page), (String)"Invalid page number (%s)", (int)page);
        return this.convertDataToPlainPage(this.pages.get(page - 1));
    }

    @Override
    public void setPage(int page, String text) {
        Preconditions.checkArgument((boolean)this.isValidPage(page), (String)"Invalid page number (%s/%s)", (int)page, (int)this.getPageCount());
        String newText = this.validatePage(text);
        this.pages.set(page - 1, this.convertPlainPageToData(newText));
    }

    @Override
    public void setPages(String ... pages) {
        this.setPages(Arrays.asList(pages));
    }

    @Override
    public void addPage(String ... pages) {
        String[] stringArray = pages;
        int n = pages.length;
        int n2 = 0;
        while (n2 < n) {
            String page = stringArray[n2];
            page = this.validatePage(page);
            this.internalAddPage(this.convertPlainPageToData(page));
            ++n2;
        }
    }

    String validatePage(String page) {
        if (page == null) {
            page = "";
        } else if (page.length() > 320) {
            page = page.substring(0, 320);
        }
        return page;
    }

    private void internalAddPage(String page) {
        if (this.pages == null) {
            this.pages = new ArrayList<String>();
        } else if (this.pages.size() >= 100) {
            return;
        }
        this.pages.add(page);
    }

    @Override
    public int getPageCount() {
        return this.pages == null ? 0 : this.pages.size();
    }

    @Override
    public List<String> getPages() {
        if (this.pages == null) {
            return ImmutableList.of();
        }
        return (List)this.pages.stream().map(this::convertDataToPlainPage).collect(ImmutableList.toImmutableList());
    }

    @Override
    public void setPages(List<String> pages) {
        if (pages.isEmpty()) {
            this.pages = null;
            return;
        }
        if (this.pages != null) {
            this.pages.clear();
        }
        for (String page : pages) {
            this.addPage(page);
        }
    }

    private boolean isValidPage(int page) {
        return page > 0 && page <= this.getPageCount();
    }

    public boolean isResolved() {
        return this.resolved == null ? false : this.resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    @Override
    public CraftMetaBook clone() {
        CraftMetaBook meta = (CraftMetaBook)super.clone();
        if (this.pages != null) {
            meta.pages = new ArrayList<String>(this.pages);
        }
        meta.spigot = meta.new SpigotMeta();
        return meta;
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasTitle()) {
            hash = 61 * hash + this.title.hashCode();
        }
        if (this.hasAuthor()) {
            hash = 61 * hash + 13 * this.author.hashCode();
        }
        if (this.pages != null) {
            hash = 61 * hash + 17 * this.pages.hashCode();
        }
        if (this.resolved != null) {
            hash = 61 * hash + 17 * this.resolved.hashCode();
        }
        if (this.hasGeneration()) {
            hash = 61 * hash + 19 * this.generation.hashCode();
        }
        return original != hash ? CraftMetaBook.class.hashCode() ^ hash : hash;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaBook var2_3) {
            return (this.hasTitle() ? that.hasTitle() && this.title.equals(that.title) : !that.hasTitle()) && (this.hasAuthor() ? that.hasAuthor() && this.author.equals(that.author) : !that.hasAuthor()) && Objects.equals(this.pages, that.pages) && Objects.equals(this.resolved, that.resolved) && (this.hasGeneration() ? that.hasGeneration() && this.generation.equals(that.generation) : !that.hasGeneration());
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaBook || this.isBookEmpty());
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasTitle()) {
            builder.put((Object)CraftMetaBook.BOOK_TITLE.BUKKIT, (Object)this.title);
        }
        if (this.hasAuthor()) {
            builder.put((Object)CraftMetaBook.BOOK_AUTHOR.BUKKIT, (Object)this.author);
        }
        if (this.pages != null) {
            builder.put((Object)CraftMetaBook.BOOK_PAGES.BUKKIT, (Object)ImmutableList.copyOf(this.pages));
        }
        if (this.resolved != null) {
            builder.put((Object)CraftMetaBook.RESOLVED.BUKKIT, (Object)this.resolved);
        }
        if (this.generation != null) {
            builder.put((Object)CraftMetaBook.GENERATION.BUKKIT, (Object)this.generation);
        }
        return builder;
    }

    @Override
    public BookMeta.Spigot spigot() {
        return this.spigot;
    }

    private class SpigotMeta
    extends BookMeta.Spigot {
        private SpigotMeta() {
        }

        private String pageToJSON(String page) {
            if (CraftMetaBook.this instanceof CraftMetaBookSigned) {
                return page;
            }
            Component component = CraftChatMessage.fromString(page, true, true)[0];
            return CraftChatMessage.toJSON(component);
        }

        private String componentsToPage(BaseComponent[] components) {
            if (CraftMetaBook.this instanceof CraftMetaBookSigned) {
                return ComponentSerializer.toString(components);
            }
            return CraftChatMessage.fromJSONComponent(ComponentSerializer.toString(components));
        }

        @Override
        public BaseComponent[] getPage(int page) {
            Preconditions.checkArgument((boolean)CraftMetaBook.this.isValidPage(page), (Object)"Invalid page number");
            return ComponentSerializer.parse(this.pageToJSON(CraftMetaBook.this.pages.get(page - 1)));
        }

        @Override
        public void setPage(int page, BaseComponent ... text) {
            if (!CraftMetaBook.this.isValidPage(page)) {
                throw new IllegalArgumentException("Invalid page number " + page + "/" + CraftMetaBook.this.getPageCount());
            }
            BaseComponent[] newText = text == null ? new BaseComponent[]{} : text;
            CraftMetaBook.this.pages.set(page - 1, this.componentsToPage(newText));
        }

        @Override
        public void setPages(BaseComponent[] ... pages) {
            this.setPages(Arrays.asList(pages));
        }

        @Override
        public void addPage(BaseComponent[] ... pages) {
            BaseComponent[][] baseComponentArray = pages;
            int n = pages.length;
            int n2 = 0;
            while (n2 < n) {
                BaseComponent[] page = baseComponentArray[n2];
                if (page == null) {
                    page = new BaseComponent[]{};
                }
                CraftMetaBook.this.internalAddPage(this.componentsToPage(page));
                ++n2;
            }
        }

        @Override
        public List<BaseComponent[]> getPages() {
            if (CraftMetaBook.this.pages == null) {
                return ImmutableList.of();
            }
            ImmutableList copy = ImmutableList.copyOf(CraftMetaBook.this.pages);
            return new AbstractList<BaseComponent[]>((List)copy){
                private final /* synthetic */ List val$copy;
                {
                    this.val$copy = list;
                }

                @Override
                public BaseComponent[] get(int index) {
                    return ComponentSerializer.parse(SpigotMeta.this.pageToJSON((String)this.val$copy.get(index)));
                }

                @Override
                public int size() {
                    return this.val$copy.size();
                }
            };
        }

        @Override
        public void setPages(List<BaseComponent[]> pages) {
            if (pages.isEmpty()) {
                CraftMetaBook.this.pages = null;
                return;
            }
            if (CraftMetaBook.this.pages != null) {
                CraftMetaBook.this.pages.clear();
            }
            for (BaseComponent[] page : pages) {
                this.addPage(new BaseComponent[][]{page});
            }
        }
    }
}

