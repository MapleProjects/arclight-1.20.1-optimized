/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.entity.vehicle.Boat$Type
 *  net.minecraft.world.item.BoatItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.mod.util.DistValidate;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={BoatItem.class})
public abstract class BoatItemMixin
extends Item {
    @Shadow
    @Final
    private static Predicate<Entity> f_40615_;
    @Shadow
    @Final
    private Boat.Type f_40616_;

    @Shadow
    protected abstract Boat m_220016_(Level var1, HitResult var2);

    public BoatItemMixin(Item.Properties properties) {
        super(properties);
    }

    @Overwrite
    @NotNull
    public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack itemstack = playerIn.m_21120_(handIn);
        BlockHitResult result = BoatItemMixin.m_41435_((Level)worldIn, (Player)playerIn, (ClipContext.Fluid)ClipContext.Fluid.ANY);
        if (result.m_6662_() == HitResult.Type.MISS) {
            return new InteractionResultHolder(InteractionResult.PASS, (Object)itemstack);
        }
        Vec3 vec3d = playerIn.m_20252_(1.0f);
        double d0 = 5.0;
        List list = worldIn.m_6249_((Entity)playerIn, playerIn.m_20191_().m_82369_(vec3d.m_82490_(5.0)).m_82400_(1.0), f_40615_);
        if (!list.isEmpty()) {
            Vec3 vec3d1 = playerIn.m_20299_(1.0f);
            for (Entity entity : list) {
                AABB axisalignedbb = entity.m_20191_().m_82400_((double)entity.m_6143_());
                if (!axisalignedbb.m_82390_(vec3d1)) continue;
                return new InteractionResultHolder(InteractionResult.PASS, (Object)itemstack);
            }
        }
        if (result.m_6662_() == HitResult.Type.BLOCK) {
            PlayerInteractEvent event;
            if (DistValidate.isValid((LevelAccessor)worldIn) && (event = CraftEventFactory.callPlayerInteractEvent(playerIn, Action.RIGHT_CLICK_BLOCK, result.m_82425_(), result.m_82434_(), itemstack, false, handIn, result.m_82450_())).isCancelled()) {
                return new InteractionResultHolder(InteractionResult.PASS, (Object)itemstack);
            }
            Boat boatentity = this.m_220016_(worldIn, (HitResult)result);
            boatentity.m_28464_(this.f_40616_);
            boatentity.m_146922_(playerIn.m_146908_());
            if (!worldIn.m_45756_((Entity)boatentity, boatentity.m_20191_().m_82400_(-0.1))) {
                return new InteractionResultHolder(InteractionResult.FAIL, (Object)itemstack);
            }
            if (!worldIn.f_46443_) {
                if (DistValidate.isValid((LevelAccessor)worldIn) && CraftEventFactory.callEntityPlaceEvent(worldIn, result.m_82425_(), result.m_82434_(), playerIn, (Entity)boatentity, handIn).isCancelled()) {
                    return new InteractionResultHolder(InteractionResult.FAIL, (Object)itemstack);
                }
                if (!worldIn.m_7967_((Entity)boatentity)) {
                    return new InteractionResultHolder(InteractionResult.PASS, (Object)itemstack);
                }
                if (!playerIn.m_150110_().f_35937_) {
                    itemstack.m_41774_(1);
                }
            }
            playerIn.m_36246_(Stats.f_12982_.m_12902_((Object)this));
            return InteractionResultHolder.m_19092_((Object)itemstack, (boolean)worldIn.m_5776_());
        }
        return new InteractionResultHolder(InteractionResult.PASS, (Object)itemstack);
    }
}

