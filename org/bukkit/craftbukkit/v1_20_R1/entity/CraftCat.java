/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.animal.Cat
 *  net.minecraft.world.entity.animal.CatVariant
 *  net.minecraft.world.item.DyeColor
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.CatVariant;
import org.bukkit.DyeColor;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTameableAnimal;
import org.bukkit.entity.Cat;

public class CraftCat
extends CraftTameableAnimal
implements Cat {
    public CraftCat(CraftServer server, net.minecraft.world.entity.animal.Cat entity) {
        super(server, (TamableAnimal)entity);
    }

    public net.minecraft.world.entity.animal.Cat getHandle() {
        return (net.minecraft.world.entity.animal.Cat)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftCat";
    }

    @Override
    public Cat.Type getCatType() {
        return Cat.Type.values()[BuiltInRegistries.f_256754_.m_7447_((Object)this.getHandle().m_28554_())];
    }

    @Override
    public void setCatType(Cat.Type type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Cannot have null Type");
        this.getHandle().m_28464_((CatVariant)BuiltInRegistries.f_256754_.m_7942_(type.ordinal()));
    }

    @Override
    public DyeColor getCollarColor() {
        return DyeColor.getByWoolData((byte)this.getHandle().m_28166_().m_41060_());
    }

    @Override
    public void setCollarColor(DyeColor color) {
        this.getHandle().m_28131_(net.minecraft.world.item.DyeColor.m_41053_((int)color.getWoolData()));
    }
}

