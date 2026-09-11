/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.ContainerOpenersCounter
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ContainerOpenersCounter.class})
public abstract class ContainerOpenersCounterMixin {
    @Shadow
    private int f_155448_;
    public boolean opened;

    @Shadow
    protected abstract void m_142292_(Level var1, BlockPos var2, BlockState var3);

    @Shadow
    protected abstract void m_142289_(Level var1, BlockPos var2, BlockState var3);

    @Shadow
    protected abstract void m_142148_(Level var1, BlockPos var2, BlockState var3, int var4, int var5);

    public void onAPIOpen(Level world, BlockPos blockposition, BlockState iblockdata) {
        this.m_142292_(world, blockposition, iblockdata);
    }

    public void onAPIClose(Level world, BlockPos blockposition, BlockState iblockdata) {
        this.m_142289_(world, blockposition, iblockdata);
    }

    public void openerAPICountChanged(Level world, BlockPos blockposition, BlockState iblockdata, int i, int j) {
        this.m_142148_(world, blockposition, iblockdata, i, j);
    }

    @Inject(method={"incrementOpeners"}, at={@At(value="HEAD")})
    private void arclight$increase(Player p_155453_, Level level, BlockPos pos, BlockState p_155456_, CallbackInfo ci) {
        int newPower;
        int oldPower = Math.max(0, Math.min(15, this.f_155448_++));
        if (level.m_8055_(pos).m_60713_(Blocks.f_50325_) && oldPower != (newPower = Math.max(0, Math.min(15, this.f_155448_)))) {
            CraftEventFactory.callRedstoneChange(level, pos, oldPower, newPower);
        }
        --this.f_155448_;
    }

    @Inject(method={"decrementOpeners"}, at={@At(value="HEAD")})
    private void arclight$decrease(Player p_155453_, Level level, BlockPos pos, BlockState p_155456_, CallbackInfo ci) {
        int newPower;
        int oldPower = Math.max(0, Math.min(15, this.f_155448_--));
        if (level.m_8055_(pos).m_60713_(Blocks.f_50325_) && oldPower != (newPower = Math.max(0, Math.min(15, this.f_155448_)))) {
            CraftEventFactory.callRedstoneChange(level, pos, oldPower, newPower);
        }
        ++this.f_155448_;
    }

    @ModifyVariable(method={"recheckOpeners"}, ordinal=0, at=@At(value="FIELD", ordinal=0, target="Lnet/minecraft/world/level/block/entity/ContainerOpenersCounter;openCount:I"))
    private int arclight$addOpens(int power) {
        return this.opened ? power + 1 : power;
    }
}

