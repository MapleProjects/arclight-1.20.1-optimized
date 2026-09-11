/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.Biome$ClimateSettings
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import java.io.File;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={CraftWorld.class}, remap=false)
public abstract class CraftWorldMixin {
    @Shadow
    @Final
    private ServerLevel world;

    @Overwrite
    public File getWorldFolder() {
        return ((ServerWorldBridge)this.world).bridge$getConvertable().m_197394_(this.world.m_46472_()).toFile();
    }

    @Redirect(method={"getHumidity(III)D"}, at=@At(value="FIELD", remap=true, target="Lnet/minecraft/world/level/biome/Biome;climateSettings:Lnet/minecraft/world/level/biome/Biome$ClimateSettings;"))
    private Biome.ClimateSettings arclight$useForgeSetting(Biome instance) {
        return instance.getModifiedClimateSettings();
    }
}

