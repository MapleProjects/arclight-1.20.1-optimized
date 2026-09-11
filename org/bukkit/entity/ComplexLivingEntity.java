/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import java.util.Set;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface ComplexLivingEntity
extends LivingEntity {
    @NotNull
    public Set<ComplexEntityPart> getParts();
}

