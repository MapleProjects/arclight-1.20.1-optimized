/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.meta.KnowledgeBookMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaKnowledgeBook
extends CraftMetaItem
implements KnowledgeBookMeta {
    static final CraftMetaItem.ItemMetaKey BOOK_RECIPES = new CraftMetaItem.ItemMetaKey("Recipes");
    static final int MAX_RECIPES = Short.MAX_VALUE;
    protected List<NamespacedKey> recipes = new ArrayList<NamespacedKey>();

    public CraftMetaKnowledgeBook(CraftMetaItem meta) {
        super(meta);
        if (meta instanceof CraftMetaKnowledgeBook) {
            CraftMetaKnowledgeBook bookMeta = (CraftMetaKnowledgeBook)meta;
            this.recipes.addAll(bookMeta.recipes);
        }
    }

    CraftMetaKnowledgeBook(CompoundTag tag) {
        super(tag);
        if (tag.m_128441_(CraftMetaKnowledgeBook.BOOK_RECIPES.NBT)) {
            ListTag pages = tag.m_128437_(CraftMetaKnowledgeBook.BOOK_RECIPES.NBT, 8);
            int i = 0;
            while (i < pages.size()) {
                String recipe = pages.m_128778_(i);
                this.addRecipe(CraftNamespacedKey.fromString(recipe));
                ++i;
            }
        }
    }

    CraftMetaKnowledgeBook(Map<String, Object> map) {
        super(map);
        Iterable pages = CraftMetaItem.SerializableMeta.getObject(Iterable.class, map, CraftMetaKnowledgeBook.BOOK_RECIPES.BUKKIT, true);
        if (pages != null) {
            for (Object page : pages) {
                if (!(page instanceof String)) continue;
                this.addRecipe(CraftNamespacedKey.fromString((String)page));
            }
        }
    }

    @Override
    void applyToItem(CompoundTag itemData) {
        super.applyToItem(itemData);
        if (this.hasRecipes()) {
            ListTag list = new ListTag();
            for (NamespacedKey recipe : this.recipes) {
                list.add((Object)StringTag.m_129297_((String)recipe.toString()));
            }
            itemData.m_128365_(CraftMetaKnowledgeBook.BOOK_RECIPES.NBT, (Tag)list);
        }
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isBookEmpty();
    }

    boolean isBookEmpty() {
        return !this.hasRecipes();
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.KNOWLEDGE_BOOK;
    }

    @Override
    public boolean hasRecipes() {
        return !this.recipes.isEmpty();
    }

    @Override
    public void addRecipe(NamespacedKey ... recipes) {
        NamespacedKey[] namespacedKeyArray = recipes;
        int n = recipes.length;
        int n2 = 0;
        while (n2 < n) {
            NamespacedKey recipe = namespacedKeyArray[n2];
            if (recipe != null) {
                if (this.recipes.size() >= Short.MAX_VALUE) {
                    return;
                }
                this.recipes.add(recipe);
            }
            ++n2;
        }
    }

    @Override
    public List<NamespacedKey> getRecipes() {
        return Collections.unmodifiableList(this.recipes);
    }

    @Override
    public void setRecipes(List<NamespacedKey> recipes) {
        this.recipes.clear();
        for (NamespacedKey recipe : recipes) {
            this.addRecipe(recipe);
        }
    }

    @Override
    public CraftMetaKnowledgeBook clone() {
        CraftMetaKnowledgeBook meta = (CraftMetaKnowledgeBook)super.clone();
        meta.recipes = new ArrayList<NamespacedKey>(this.recipes);
        return meta;
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasRecipes()) {
            hash = 61 * hash + 17 * this.recipes.hashCode();
        }
        return original != hash ? CraftMetaKnowledgeBook.class.hashCode() ^ hash : hash;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaKnowledgeBook) {
            CraftMetaKnowledgeBook that = (CraftMetaKnowledgeBook)meta;
            return this.hasRecipes() ? that.hasRecipes() && this.recipes.equals(that.recipes) : !that.hasRecipes();
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaKnowledgeBook || this.isBookEmpty());
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasRecipes()) {
            ArrayList<String> recipesString = new ArrayList<String>();
            for (NamespacedKey recipe : this.recipes) {
                recipesString.add(recipe.toString());
            }
            builder.put((Object)CraftMetaKnowledgeBook.BOOK_RECIPES.BUKKIT, recipesString);
        }
        return builder;
    }
}

