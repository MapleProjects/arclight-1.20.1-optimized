/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.ChestedHorse;
import org.bukkit.inventory.LlamaInventory;
import org.jetbrains.annotations.NotNull;

public interface Llama
extends ChestedHorse {
    @NotNull
    public Color getColor();

    public void setColor(@NotNull Color var1);

    public int getStrength();

    public void setStrength(int var1);

    @Override
    @NotNull
    public LlamaInventory getInventory();

    public static enum Color {
        CREAMY,
        WHITE,
        BROWN,
        GRAY;

    }
}

