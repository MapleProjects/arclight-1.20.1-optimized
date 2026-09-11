/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.chunk.LevelChunkSection
 *  net.minecraft.world.level.chunk.PalettedContainer
 *  net.minecraft.world.level.chunk.PalettedContainerRO
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.chunk;

import io.izzel.arclight.common.bridge.core.world.chunk.LevelChunkSectionBridge;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.PalettedContainerRO;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={LevelChunkSection.class})
public class LevelChunkSectionMixin
implements LevelChunkSectionBridge {
    @Shadow
    private PalettedContainerRO<Holder<Biome>> f_187995_;

    public void setBiome(int i, int j, int k, Holder<Biome> biome) {
        ((PalettedContainer)this.f_187995_).m_156470_(i, j, k, biome);
    }

    @Override
    public void bridge$setBiome(int x, int y, int z, Holder<Biome> biome) {
        this.setBiome(x, y, z, biome);
    }
}

