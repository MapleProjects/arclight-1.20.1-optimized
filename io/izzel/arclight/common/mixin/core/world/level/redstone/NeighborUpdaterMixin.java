/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.CrashReport
 *  net.minecraft.CrashReportCategory
 *  net.minecraft.ReportedException
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelHeightAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.redstone.NeighborUpdater
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.level.redstone;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import java.util.Locale;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.NeighborUpdater;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={NeighborUpdater.class})
public interface NeighborUpdaterMixin {
    @Overwrite
    public static void m_230763_(Level level, BlockState state, BlockPos pos, Block block, BlockPos source, boolean p_230769_) {
        try {
            CraftWorld cworld = ((WorldBridge)level).bridge$getWorld();
            if (cworld != null) {
                BlockPhysicsEvent event = new BlockPhysicsEvent(CraftBlock.at((LevelAccessor)level, pos), CraftBlockData.fromData(state), CraftBlock.at((LevelAccessor)level, source));
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) {
                    return;
                }
            }
            state.m_60690_(level, pos, block, source, p_230769_);
        }
        catch (StackOverflowError ex) {
            ((WorldBridge)level).bridge$setLastPhysicsProblem(pos.m_7949_());
        }
        catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.m_127521_((Throwable)throwable, (String)"Exception while updating neighbours");
            CrashReportCategory crashreportcategory = crashreport.m_127514_("Block being updated");
            crashreportcategory.m_128165_("Source block type", () -> {
                try {
                    return String.format(Locale.ROOT, "ID #%s (%s // %s)", BuiltInRegistries.f_256975_.m_7981_((Object)block), block.m_7705_(), block.getClass().getCanonicalName());
                }
                catch (Throwable throwable1) {
                    return "ID #" + String.valueOf(BuiltInRegistries.f_256975_.m_7981_((Object)block));
                }
            });
            CrashReportCategory.m_178950_((CrashReportCategory)crashreportcategory, (LevelHeightAccessor)level, (BlockPos)pos, (BlockState)state);
            throw new ReportedException(crashreport);
        }
    }
}

