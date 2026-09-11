/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate$Palette
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.levelgen.structure.templatesystem;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={StructurePlaceSettings.class})
public class StructurePlaceSettingsMixin {
    @Shadow
    private int f_74369_;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(CallbackInfo ci) {
        this.f_74369_ = -1;
    }

    @Inject(method={"getRandomPalette"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;getRandom(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/util/RandomSource;")})
    private void arclight$forcePalette(List<StructureTemplate.Palette> list, BlockPos p_74389_, CallbackInfoReturnable<StructureTemplate.Palette> cir) {
        int i = list.size();
        if (this.f_74369_ > 0) {
            if (this.f_74369_ >= i) {
                throw new IllegalArgumentException("Palette index out of bounds. Got " + this.f_74369_ + " where there are only " + i + " palettes available.");
            }
            cir.setReturnValue((Object)list.get(this.f_74369_));
        }
    }
}

