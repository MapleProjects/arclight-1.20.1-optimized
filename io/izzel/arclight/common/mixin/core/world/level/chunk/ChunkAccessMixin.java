/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.CrashReport
 *  net.minecraft.CrashReportCategory
 *  net.minecraft.ReportedException
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.QuartPos
 *  net.minecraft.core.Registry
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.util.Mth
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.LevelHeightAccessor
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.BiomeManager$NoiseBiomeSource
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.LevelChunkSection
 *  net.minecraft.world.level.chunk.UpgradeData
 *  net.minecraft.world.level.levelgen.blending.BlendingData
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.chunk;

import io.izzel.arclight.common.bridge.core.world.chunk.ChunkAccessBridge;
import io.izzel.arclight.common.bridge.core.world.chunk.LevelChunkSectionBridge;
import java.util.Map;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.UpgradeData;
import net.minecraft.world.level.levelgen.blending.BlendingData;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.craftbukkit.v1_20_R1.persistence.DirtyCraftPersistentDataContainer;
import org.bukkit.persistence.PersistentDataContainer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ChunkAccess.class})
public abstract class ChunkAccessMixin
implements BlockGetter,
BiomeManager.NoiseBiomeSource,
ChunkAccessBridge {
    @Shadow
    @Final
    protected LevelChunkSection[] f_187612_;
    @Shadow
    @Final
    protected Map<BlockPos, CompoundTag> f_187609_;
    @Shadow
    @Final
    protected ChunkPos f_187604_;
    private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
    public DirtyCraftPersistentDataContainer persistentDataContainer = new DirtyCraftPersistentDataContainer(DATA_TYPE_REGISTRY);
    public Registry<Biome> biomeRegistry;

    @Shadow
    public abstract void m_8092_(boolean var1);

    @Shadow
    public abstract int m_141937_();

    @Shadow
    public abstract int m_141928_();

    @Shadow
    public boolean m_6344_() {
        return false;
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(ChunkPos p_187621_, UpgradeData p_187622_, LevelHeightAccessor p_187623_, Registry<Biome> registry, long p_187625_, LevelChunkSection[] p_187626_, BlendingData p_187627_, CallbackInfo ci) {
        this.biomeRegistry = registry;
    }

    @Inject(method={"setUnsaved"}, at={@At(value="HEAD")})
    private void arclight$dirty(boolean flag, CallbackInfo ci) {
        if (!flag) {
            this.persistentDataContainer.dirty(false);
        }
    }

    @Inject(method={"isUnsaved"}, cancellable=true, at={@At(value="RETURN")})
    private void arclight$isDirty(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((Object)(cir.getReturnValueZ() || this.persistentDataContainer.dirty() ? 1 : 0));
    }

    @Override
    public PersistentDataContainer bridge$getPersistentDataContainer() {
        return this.persistentDataContainer;
    }

    public void setBiome(int i, int j, int k, Holder<Biome> biome) {
        try {
            int l = QuartPos.m_175400_((int)this.m_141937_());
            int i1 = l + QuartPos.m_175400_((int)this.m_141928_()) - 1;
            int j1 = Mth.m_14045_((int)j, (int)l, (int)i1);
            int k1 = this.m_151564_(QuartPos.m_175402_((int)j1));
            ((LevelChunkSectionBridge)this.f_187612_[k1]).bridge$setBiome(i & 3, j1 & 3, k & 3, biome);
        }
        catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.m_127521_((Throwable)throwable, (String)"Setting biome");
            CrashReportCategory crashreportsystemdetails = crashreport.m_127514_("Biome being set");
            crashreportsystemdetails.m_128165_("Location", () -> CrashReportCategory.m_178942_((LevelHeightAccessor)this, (int)i, (int)j, (int)k));
            throw new ReportedException(crashreport);
        }
    }
}

