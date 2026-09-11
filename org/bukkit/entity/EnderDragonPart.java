/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragon;
import org.jetbrains.annotations.NotNull;

public interface EnderDragonPart
extends ComplexEntityPart,
Damageable {
    @Override
    @NotNull
    public EnderDragon getParent();
}

