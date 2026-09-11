/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface ComplexEntityPart
extends Entity {
    @NotNull
    public ComplexLivingEntity getParent();
}

