/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.storage.LevelStorageSource
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 *  net.minecraft.world.level.validation.ContentValidationException
 *  net.minecraft.world.level.validation.DirectoryValidator
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.storage;

import io.izzel.arclight.common.bridge.core.world.storage.LevelStorageSourceBridge;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.validation.ContentValidationException;
import net.minecraft.world.level.validation.DirectoryValidator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={LevelStorageSource.class})
public abstract class LevelStorageSourceMixin
implements LevelStorageSourceBridge {
    @Shadow
    @Final
    private DirectoryValidator f_289816_;

    @Shadow
    public abstract LevelStorageSource.LevelStorageAccess m_78260_(String var1) throws IOException;

    @Shadow
    protected abstract Path m_289874_(String var1);

    public LevelStorageSource.LevelStorageAccess validateAndCreateAccess(String s, ResourceKey<LevelStem> dimensionType) throws IOException, ContentValidationException {
        Path path = this.m_289874_(s);
        List list = this.f_289816_.m_289885_(path, true);
        if (!list.isEmpty()) {
            throw new ContentValidationException(path, list);
        }
        LevelStorageSource.LevelStorageAccess save = this.m_78260_(s);
        ((LevelStorageSourceBridge.LevelStorageAccessBridge)save).bridge$setDimType(dimensionType);
        return save;
    }

    public LevelStorageSource.LevelStorageAccess createAccess(String saveName, ResourceKey<LevelStem> world) throws IOException {
        LevelStorageSource.LevelStorageAccess save = this.m_78260_(saveName);
        ((LevelStorageSourceBridge.LevelStorageAccessBridge)save).bridge$setDimType(world);
        return save;
    }

    @Override
    public LevelStorageSource.LevelStorageAccess bridge$getLevelSave(String saveName, ResourceKey<LevelStem> world) throws IOException {
        return this.createAccess(saveName, world);
    }
}

