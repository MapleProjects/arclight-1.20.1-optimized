/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.ArclightConfig
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.storage.DerivedLevelData
 *  net.minecraft.world.level.storage.ServerLevelData
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.storage;

import io.izzel.arclight.common.bridge.core.world.storage.DerivedWorldInfoBridge;
import io.izzel.arclight.i18n.ArclightConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={DerivedLevelData.class})
public class DerivedWorldInfoMixin
implements DerivedWorldInfoBridge {
    @Shadow
    @Final
    private ServerLevelData f_78077_;
    private ResourceKey<LevelStem> typeKey;

    @Overwrite
    public String m_5462_() {
        if (this.typeKey == null || this.typeKey == LevelStem.f_63971_) {
            return this.f_78077_.m_5462_();
        }
        if (ArclightConfig.spec().getCompat().isSymlinkWorld()) {
            String worldName = this.f_78077_.m_5462_() + "_";
            String suffix = this.typeKey == LevelStem.f_63972_ ? "nether" : (this.typeKey == LevelStem.f_63973_ ? "the_end" : (this.typeKey.m_135782_().m_135827_() + "_" + this.typeKey.m_135782_().m_135815_()).replace('/', '_'));
            return worldName + suffix;
        }
        String worldName = this.f_78077_.m_5462_() + "/";
        Object suffix = this.typeKey == LevelStem.f_63973_ ? "DIM1" : (this.typeKey == LevelStem.f_63972_ ? "DIM-1" : this.typeKey.m_135782_().m_135827_() + "/" + this.typeKey.m_135782_().m_135815_());
        return worldName + (String)suffix;
    }

    @Override
    public ServerLevelData bridge$getDelegate() {
        return this.f_78077_;
    }

    @Override
    public void bridge$setDimType(ResourceKey<LevelStem> typeKey) {
        this.typeKey = typeKey;
    }
}

