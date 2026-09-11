/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block;

import org.bukkit.block.Container;
import org.bukkit.loot.Lootable;

public interface Dropper
extends Container,
Lootable {
    public void drop();
}

