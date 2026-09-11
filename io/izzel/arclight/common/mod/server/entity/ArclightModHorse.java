/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.server.entity;

import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractHorse;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Horse;
import org.jetbrains.annotations.NotNull;

public class ArclightModHorse
extends CraftAbstractHorse {
    public ArclightModHorse(CraftServer server, AbstractHorse entity) {
        super(server, entity);
    }

    @Override
    public  @NotNull Horse.Variant getVariant() {
        return Horse.Variant.HORSE;
    }

    @Override
    @NotNull
    public EntityCategory getCategory() {
        return EntityCategory.NONE;
    }
}

