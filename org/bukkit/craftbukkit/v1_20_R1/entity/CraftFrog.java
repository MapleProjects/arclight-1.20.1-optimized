/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.FrogVariant
 *  net.minecraft.world.entity.animal.frog.Frog
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Frog;

public class CraftFrog
extends CraftAnimals
implements org.bukkit.entity.Frog {
    public CraftFrog(CraftServer server, Frog entity) {
        super(server, (Animal)entity);
    }

    public Frog getHandle() {
        return (Frog)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFrog";
    }

    @Override
    public Entity getTongueTarget() {
        return this.getHandle().m_218538_().map(net.minecraft.world.entity.Entity::getBukkitEntity).orElse(null);
    }

    @Override
    public void setTongueTarget(Entity target) {
        if (target == null) {
            this.getHandle().m_218536_();
        } else {
            this.getHandle().m_218481_(((CraftEntity)target).getHandle());
        }
    }

    @Override
    public Frog.Variant getVariant() {
        return Registry.FROG_VARIANT.get(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.f_256770_.m_7981_((Object)this.getHandle().m_28554_())));
    }

    @Override
    public void setVariant(Frog.Variant variant) {
        Preconditions.checkArgument((variant != null ? 1 : 0) != 0, (Object)"variant");
        this.getHandle().m_28464_((FrogVariant)BuiltInRegistries.f_256770_.m_7745_(CraftNamespacedKey.toMinecraft(variant.getKey())));
    }
}

