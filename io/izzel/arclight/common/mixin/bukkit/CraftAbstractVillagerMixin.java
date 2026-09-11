/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.mixin.bukkit.CraftEntityMixin;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={CraftAbstractVillager.class}, remap=false)
public abstract class CraftAbstractVillagerMixin
extends CraftEntityMixin {
    @Overwrite
    public AbstractVillager getHandle() {
        return (AbstractVillager)this.entity;
    }
}

