/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.Container
 *  net.minecraft.world.Containers
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.CampfireCookingRecipe
 *  net.minecraft.world.item.crafting.RecipeManager$CachedCheck
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.CampfireBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.BlockEntityMixin;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.block.CampfireStartEvent;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={CampfireBlockEntity.class})
public abstract class CampfireBlockEntityMixin
extends BlockEntityMixin {
    @Shadow
    @Final
    private RecipeManager.CachedCheck<Container, CampfireCookingRecipe> f_222760_;
    @Shadow
    @Final
    public int[] f_59044_;

    @Shadow
    public abstract Optional<CampfireCookingRecipe> m_59051_(net.minecraft.world.item.ItemStack var1);

    @Overwrite
    public static void m_155306_(Level level, BlockPos pos, BlockState state, CampfireBlockEntity entity) {
        boolean flag = false;
        for (int i = 0; i < entity.m_59065_().size(); ++i) {
            SimpleContainer container;
            net.minecraft.world.item.ItemStack itemstack1;
            net.minecraft.world.item.ItemStack itemstack = (net.minecraft.world.item.ItemStack)entity.m_59065_().get(i);
            if (itemstack.m_41619_()) continue;
            flag = true;
            int n = i;
            entity.f_59043_[n] = entity.f_59043_[n] + 1;
            if (entity.f_59043_[i] < entity.f_59044_[i] || !(itemstack1 = ((CampfireBlockEntityMixin)entity).f_222760_.m_213657_((Container)(container = new SimpleContainer(new net.minecraft.world.item.ItemStack[]{itemstack})), level).map(arg_0 -> CampfireBlockEntityMixin.lambda$cookTick$0((Container)container, level, arg_0)).orElse(itemstack)).m_246617_(level.m_246046_())) continue;
            CraftItemStack source = CraftItemStack.asCraftMirror(itemstack);
            ItemStack result = CraftItemStack.asBukkitCopy(itemstack1);
            BlockCookEvent blockCookEvent = new BlockCookEvent(CraftBlock.at((LevelAccessor)level, pos), source, result);
            Bukkit.getPluginManager().callEvent(blockCookEvent);
            if (blockCookEvent.isCancelled()) {
                return;
            }
            result = blockCookEvent.getResult();
            itemstack1 = CraftItemStack.asNMSCopy(result);
            Containers.m_18992_((Level)level, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), (net.minecraft.world.item.ItemStack)itemstack1);
            entity.m_59065_().set(i, (Object)net.minecraft.world.item.ItemStack.f_41583_);
            level.m_7260_(pos, state, state, 3);
            level.m_220407_(GameEvent.f_157792_, pos, GameEvent.Context.m_223722_((BlockState)state));
        }
        if (flag) {
            CampfireBlockEntityMixin.m_155232_(level, pos, state);
        }
    }

    @Inject(method={"placeFood"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="FIELD", target="Lnet/minecraft/world/level/block/entity/CampfireBlockEntity;cookingProgress:[I")})
    private void arclight$cookStart(Entity p_238285_, net.minecraft.world.item.ItemStack stack, int p_238287_, CallbackInfoReturnable<Boolean> cir, int i) {
        CampfireStartEvent event = new CampfireStartEvent(CraftBlock.at((LevelAccessor)this.f_58857_, this.f_58858_), CraftItemStack.asCraftMirror(stack), (CampfireRecipe)((IRecipeBridge)this.m_59051_(stack).get()).bridge$toBukkitRecipe());
        Bukkit.getPluginManager().callEvent(event);
        this.f_59044_[i] = event.getTotalCookTime();
    }

    private static /* synthetic */ net.minecraft.world.item.ItemStack lambda$cookTick$0(Container container, Level level, CampfireCookingRecipe p_155305_) {
        return p_155305_.m_5874_(container, level.m_9598_());
    }
}

