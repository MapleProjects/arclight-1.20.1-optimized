/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.LocalizedException
 *  io.izzel.arclight.i18n.conf.EntityPropertySpec
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.bukkit.EntityTypeBridge;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.i18n.LocalizedException;
import io.izzel.arclight.i18n.conf.EntityPropertySpec;
import java.util.function.Function;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={EntityType.class}, remap=false)
public class EntityTypeMixin
implements EntityTypeBridge {
    @Shadow
    @Final
    @Mutable
    private NamespacedKey key;
    @Shadow
    @Final
    @Mutable
    private Class<? extends Entity> clazz;
    @Shadow
    @Final
    @Mutable
    private String name;
    private net.minecraft.world.entity.EntityType<?> handleType;
    private EntityPropertySpec spec;
    private Function<Location, ? extends net.minecraft.world.entity.Entity> factory;

    @Override
    public void bridge$setup(ResourceLocation location, net.minecraft.world.entity.EntityType<?> entityType, EntityPropertySpec spec) {
        this.key = CraftNamespacedKey.fromMinecraft(location);
        this.name = location.toString();
        this.handleType = entityType;
        this.spec = spec.clone();
        this.setup();
    }

    private void setup() {
        if (this.spec.entityClass != null) {
            try {
                Class<?> cl = Class.forName(this.spec.entityClass);
                if (!Entity.class.isAssignableFrom(cl)) {
                    throw LocalizedException.checked((String)"registry.entity.not-subclass", (Object[])new Object[]{cl, Entity.class});
                }
                this.clazz = cl;
            }
            catch (Exception e) {
                if (e instanceof LocalizedException) {
                    ArclightMod.LOGGER.warn(((LocalizedException)e).node(), ((LocalizedException)e).args());
                }
                ArclightMod.LOGGER.warn("registry.entity.error", (Object)this, (Object)this.spec.entityClass, (Object)e);
            }
        }
        this.factory = loc -> {
            if (loc != null && loc.getWorld() != null) {
                ServerLevel world = ((CraftWorld)loc.getWorld()).getHandle();
                net.minecraft.world.entity.Entity entity = this.handleType.m_20615_((Level)world);
                if (entity != null) {
                    entity.m_19890_(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                }
                return entity;
            }
            return null;
        };
    }

    @Override
    public net.minecraft.world.entity.EntityType<?> bridge$getHandle() {
        return this.handleType;
    }

    @Override
    public void bridge$setHandle(net.minecraft.world.entity.EntityType<?> entityType) {
        this.handleType = entityType;
    }

    @Override
    public EntityPropertySpec bridge$getSpec() {
        return this.spec;
    }

    @Override
    public Function<Location, ? extends net.minecraft.world.entity.Entity> bridge$entityFactory() {
        return this.factory;
    }

    @Override
    public void bridge$setEntityFactory(Function<Location, ? extends net.minecraft.world.entity.Entity> function) {
        this.factory = function;
    }
}

