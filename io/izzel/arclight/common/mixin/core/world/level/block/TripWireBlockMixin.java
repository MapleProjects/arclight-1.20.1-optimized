/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.TripWireBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.BlockMixin;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={TripWireBlock.class})
public abstract class TripWireBlockMixin
extends BlockMixin {
    @Shadow
    @Final
    public static BooleanProperty f_57590_;

    @Shadow
    protected abstract void m_57610_(Level var1, BlockPos var2, BlockState var3);

    @Overwrite
    private void m_57607_(Level worldIn, BlockPos pos) {
        BlockState blockstate = worldIn.m_8055_(pos);
        boolean flag = (Boolean)blockstate.m_61143_((Property)f_57590_);
        boolean flag1 = false;
        List list = worldIn.m_45933_(null, blockstate.m_60808_((BlockGetter)worldIn, pos).m_83215_().m_82338_(pos));
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (entity.m_6090_()) continue;
                flag1 = true;
                break;
            }
        }
        if (flag != flag1 && flag1 && ((Boolean)blockstate.m_61143_((Property)TripWireBlock.f_57591_)).booleanValue()) {
            CraftBlock block = CraftBlock.at((LevelAccessor)worldIn, pos);
            boolean allowed = false;
            for (Object object : list) {
                Event cancellable;
                if (object == null) continue;
                if (object instanceof Player) {
                    cancellable = CraftEventFactory.callPlayerInteractEvent((Player)object, Action.PHYSICAL, pos, null, null, null);
                } else {
                    if (!(object instanceof Entity)) continue;
                    cancellable = new EntityInteractEvent(((EntityBridge)object).bridge$getBukkitEntity(), block);
                    Bukkit.getPluginManager().callEvent((EntityInteractEvent)cancellable);
                }
                if (cancellable.isCancelled()) continue;
                allowed = true;
                break;
            }
            if (!allowed) {
                return;
            }
        }
        if (flag1 != flag) {
            blockstate = (BlockState)blockstate.m_61124_((Property)f_57590_, (Comparable)Boolean.valueOf(flag1));
            worldIn.m_7731_(pos, blockstate, 3);
            this.m_57610_(worldIn, pos, blockstate);
        }
        if (flag1) {
            worldIn.m_186460_(new BlockPos((Vec3i)pos), (Block)this, 10);
        }
    }
}

