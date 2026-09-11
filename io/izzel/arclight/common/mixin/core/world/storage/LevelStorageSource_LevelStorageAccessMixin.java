/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.storage.LevelStorageSource
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelDirectory
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.storage;

import io.izzel.arclight.common.bridge.core.world.storage.LevelStorageSourceBridge;
import java.nio.file.Path;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LevelStorageSource.LevelStorageAccess.class})
public class LevelStorageSource_LevelStorageAccessMixin
implements LevelStorageSourceBridge.LevelStorageAccessBridge {
    @Shadow
    @Final
    LevelStorageSource.LevelDirectory f_230867_;
    public ResourceKey<LevelStem> dimensionType;

    public void arclight$constructor(LevelStorageSource saveFormat, String saveName) {
        throw new RuntimeException();
    }

    public void arclight$constructor(LevelStorageSource saveFormat, String saveName, ResourceKey<LevelStem> dimensionType) {
        this.arclight$constructor(saveFormat, saveName);
        this.dimensionType = dimensionType;
    }

    @Override
    public void bridge$setDimType(ResourceKey<LevelStem> typeKey) {
        this.dimensionType = typeKey;
    }

    @Override
    public ResourceKey<LevelStem> bridge$getTypeKey() {
        return this.dimensionType;
    }

    @Inject(method={"getDimensionPath"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$useActualType(ResourceKey<Level> dimensionKey, CallbackInfoReturnable<Path> cir) {
        if (this.dimensionType == LevelStem.f_63971_) {
            cir.setReturnValue((Object)this.f_230867_.f_230850_());
        } else if (this.dimensionType == LevelStem.f_63972_) {
            cir.setReturnValue((Object)this.f_230867_.f_230850_().resolve("DIM-1"));
        } else if (this.dimensionType == LevelStem.f_63973_) {
            cir.setReturnValue((Object)this.f_230867_.f_230850_().resolve("DIM1"));
        }
    }
}

