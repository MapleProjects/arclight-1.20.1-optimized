/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public interface Boss
extends Entity {
    @Nullable
    public BossBar getBossBar();
}

