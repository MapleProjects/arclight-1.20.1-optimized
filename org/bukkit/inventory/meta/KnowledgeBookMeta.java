/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface KnowledgeBookMeta
extends ItemMeta {
    public boolean hasRecipes();

    @NotNull
    public List<NamespacedKey> getRecipes();

    public void setRecipes(@NotNull List<NamespacedKey> var1);

    public void addRecipe(NamespacedKey ... var1);

    @Override
    @NotNull
    public KnowledgeBookMeta clone();
}

