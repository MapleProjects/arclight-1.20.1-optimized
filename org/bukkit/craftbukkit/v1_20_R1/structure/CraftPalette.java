/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate$Palette
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate$StructureBlockInfo
 */
package org.bukkit.craftbukkit.v1_20_R1.structure;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.structure.Palette;

public class CraftPalette
implements Palette {
    private final StructureTemplate.Palette palette;

    public CraftPalette(StructureTemplate.Palette palette) {
        this.palette = palette;
    }

    @Override
    public List<BlockState> getBlocks() {
        ArrayList<BlockState> blocks = new ArrayList<BlockState>();
        for (StructureTemplate.StructureBlockInfo blockInfo : this.palette.m_74652_()) {
            blocks.add(CraftBlockStates.getBlockState(blockInfo.f_74675_(), blockInfo.f_74676_(), blockInfo.f_74677_()));
        }
        return blocks;
    }

    @Override
    public int getBlockCount() {
        return this.palette.m_74652_().size();
    }
}

