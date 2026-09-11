/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate$Palette
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate$StructureBlockInfo
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.levelgen.structure.templatesystem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={StructureTemplate.Palette.class})
public abstract class StructureTemplate_PaletteMixin {
    @Shadow
    @Final
    private List<StructureTemplate.StructureBlockInfo> f_74645_;
    @Shadow
    @Final
    private Map<Block, List<StructureTemplate.StructureBlockInfo>> f_74646_;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Overwrite
    public List<StructureTemplate.StructureBlockInfo> m_74653_(Block block) {
        Map<Block, List<StructureTemplate.StructureBlockInfo>> map = this.f_74646_;
        synchronized (map) {
            return this.f_74646_.computeIfAbsent(block, b -> this.f_74645_.stream().filter(info -> info.f_74676_().m_60713_(b)).collect(Collectors.toList()));
        }
    }
}

