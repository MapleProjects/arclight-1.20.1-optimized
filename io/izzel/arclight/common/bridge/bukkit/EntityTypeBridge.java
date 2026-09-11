/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.conf.EntityPropertySpec
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 */
package io.izzel.arclight.common.bridge.bukkit;

import io.izzel.arclight.i18n.conf.EntityPropertySpec;
import java.util.function.Function;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Location;

public interface EntityTypeBridge {
    public void bridge$setup(ResourceLocation var1, EntityType<?> var2, EntityPropertySpec var3);

    public EntityType<?> bridge$getHandle();

    public void bridge$setHandle(EntityType<?> var1);

    public EntityPropertySpec bridge$getSpec();

    public Function<Location, ? extends Entity> bridge$entityFactory();

    public void bridge$setEntityFactory(Function<Location, ? extends Entity> var1);
}

