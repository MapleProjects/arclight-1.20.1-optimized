/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.NonNullList
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.Slice
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import io.izzel.arclight.common.bridge.core.tileentity.AbstractFurnaceTileEntityBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.LockableBlockEntityMixin;
import io.izzel.arclight.mixin.Eject;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={AbstractFurnaceBlockEntity.class})
public abstract class AbstractFurnaceBlockEntityMixin
extends LockableBlockEntityMixin
implements AbstractFurnaceTileEntityBridge {
    @Shadow
    protected NonNullList<net.minecraft.world.item.ItemStack> f_58310_;
    @Shadow
    @Final
    private Object2IntOpenHashMap<ResourceLocation> f_58320_;
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();
    private int maxStack = 64;
    private static AbstractFurnaceBlockEntity arclight$captureFurnace;
    private static Player arclight$capturePlayer;
    private static net.minecraft.world.item.ItemStack arclight$item;
    private static int arclight$captureAmount;

    @Shadow
    protected abstract int m_7743_(net.minecraft.world.item.ItemStack var1);

    @Shadow
    protected abstract boolean m_58425_();

    @Shadow
    public abstract List<Recipe<?>> m_154995_(ServerLevel var1, Vec3 var2);

    @Shadow
    protected abstract boolean m_155005_(RegistryAccess var1, @Nullable Recipe<?> var2, NonNullList<net.minecraft.world.item.ItemStack> var3, int var4);

    @Eject(method={"serverTick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;isLit()Z"), slice=@Slice(from=@At(value="FIELD", target="Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;litDuration:I"), to=@At(value="INVOKE", remap=false, target="Lnet/minecraft/world/item/ItemStack;hasCraftingRemainingItem()Z")))
    private static boolean arclight$setBurnTime(AbstractFurnaceBlockEntity furnace, CallbackInfo ci) {
        net.minecraft.world.item.ItemStack itemStack = furnace.m_8020_(1);
        CraftItemStack fuel = CraftItemStack.asCraftMirror(itemStack);
        FurnaceBurnEvent furnaceBurnEvent = new FurnaceBurnEvent(CraftBlock.at((LevelAccessor)furnace.f_58857_, furnace.m_58899_()), fuel, ((AbstractFurnaceTileEntityBridge)furnace).bridge$getBurnDuration(itemStack));
        Bukkit.getPluginManager().callEvent(furnaceBurnEvent);
        if (furnaceBurnEvent.isCancelled()) {
            ci.cancel();
            return false;
        }
        return ((AbstractFurnaceTileEntityBridge)furnace).bridge$isLit() && furnaceBurnEvent.isBurning();
    }

    @Inject(method={"serverTick"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="FIELD", ordinal=0, target="Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;cookingProgress:I")})
    private static void arclight$startSmelt(Level level, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity furnace, CallbackInfo ci, boolean flag, boolean flag1, net.minecraft.world.item.ItemStack stack, boolean flag2, boolean flag3, Recipe<?> recipe) {
        if (recipe != null && furnace.f_58318_ == 0) {
            CraftItemStack source = CraftItemStack.asCraftMirror(furnace.m_8020_(0));
            org.bukkit.inventory.Recipe recipe2 = ((IRecipeBridge)recipe).bridge$toBukkitRecipe();
            if (recipe2 instanceof CookingRecipe) {
                CookingRecipe cookingRecipe = (CookingRecipe)recipe2;
                FurnaceStartSmeltEvent event = new FurnaceStartSmeltEvent(CraftBlock.at((LevelAccessor)level, pos), source, cookingRecipe);
                Bukkit.getPluginManager().callEvent(event);
                furnace.f_58319_ = event.getTotalCookTime();
            }
        }
    }

    @Overwrite
    private boolean m_266209_(RegistryAccess registryAccess, @javax.annotation.Nullable Recipe<?> recipe, NonNullList<net.minecraft.world.item.ItemStack> items, int i) {
        if (recipe != null && this.m_155005_(registryAccess, recipe, items, i)) {
            net.minecraft.world.item.ItemStack itemstack = (net.minecraft.world.item.ItemStack)items.get(0);
            net.minecraft.world.item.ItemStack itemstack1 = recipe.m_5874_((Container)((AbstractFurnaceBlockEntity)this), registryAccess);
            net.minecraft.world.item.ItemStack itemstack2 = (net.minecraft.world.item.ItemStack)items.get(2);
            CraftItemStack source = CraftItemStack.asCraftMirror(itemstack);
            ItemStack result = CraftItemStack.asBukkitCopy(itemstack1);
            FurnaceSmeltEvent furnaceSmeltEvent = new FurnaceSmeltEvent(CraftBlock.at((LevelAccessor)this.f_58857_, this.f_58858_), source, result);
            Bukkit.getPluginManager().callEvent(furnaceSmeltEvent);
            if (furnaceSmeltEvent.isCancelled()) {
                return false;
            }
            result = furnaceSmeltEvent.getResult();
            itemstack1 = CraftItemStack.asNMSCopy(result);
            if (!itemstack1.m_41619_()) {
                if (itemstack2.m_41619_()) {
                    items.set(2, (Object)itemstack1.m_41777_());
                } else if (CraftItemStack.asCraftMirror(itemstack2).isSimilar(result)) {
                    itemstack2.m_41769_(itemstack1.m_41613_());
                } else {
                    return false;
                }
            }
            if (itemstack.m_150930_(Blocks.f_50057_.m_5456_()) && !((net.minecraft.world.item.ItemStack)items.get(1)).m_41619_() && ((net.minecraft.world.item.ItemStack)items.get(1)).m_150930_(Items.f_42446_)) {
                items.set(1, (Object)new net.minecraft.world.item.ItemStack((ItemLike)Items.f_42447_));
            }
            itemstack.m_41774_(1);
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel world, Vec3 vec, BlockPos pos, Player entity, net.minecraft.world.item.ItemStack itemStack, int amount) {
        try {
            arclight$item = itemStack;
            arclight$captureAmount = amount;
            arclight$captureFurnace = (AbstractFurnaceBlockEntity)this;
            arclight$capturePlayer = entity;
            List<Recipe<?>> list = this.m_154995_(world, vec);
            entity.m_7281_(list);
            this.f_58320_.clear();
            List<Recipe<?>> list2 = list;
            return list2;
        }
        finally {
            arclight$item = null;
            arclight$captureAmount = 0;
            arclight$captureFurnace = null;
            arclight$capturePlayer = null;
        }
    }

    @Override
    public List<Recipe<?>> bridge$dropExp(ServerPlayer entity, net.minecraft.world.item.ItemStack itemStack, int amount) {
        return this.getRecipesToAwardAndPopExperience(entity.m_284548_(), entity.m_20182_(), this.f_58858_, (Player)entity, itemStack, amount);
    }

    @Redirect(method={"createExperience"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/ExperienceOrb;award(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)V"))
    private static void arclight$expEvent(ServerLevel level, Vec3 vec3, int amount) {
        if (arclight$capturePlayer != null && arclight$captureAmount != 0) {
            FurnaceExtractEvent event = new FurnaceExtractEvent(((ServerPlayerEntityBridge)arclight$capturePlayer).bridge$getBukkitEntity(), CraftBlock.at((LevelAccessor)level, arclight$captureFurnace.m_58899_()), CraftMagicNumbers.getMaterial(arclight$item.m_41720_()), arclight$captureAmount, amount);
            Bukkit.getPluginManager().callEvent(event);
            amount = event.getExpToDrop();
        }
        ExperienceOrb.m_147082_((ServerLevel)level, (Vec3)vec3, (int)amount);
    }

    @Override
    public List<net.minecraft.world.item.ItemStack> getContents() {
        return this.f_58310_;
    }

    @Override
    public void onOpen(CraftHumanEntity who) {
        this.transaction.add(who);
    }

    @Override
    public void onClose(CraftHumanEntity who) {
        this.transaction.remove(who);
    }

    @Override
    public List<HumanEntity> getViewers() {
        return this.transaction;
    }

    @Override
    public void setOwner(InventoryHolder owner) {
    }

    public int m_6893_() {
        if (this.maxStack == 0) {
            this.maxStack = 64;
        }
        return this.maxStack;
    }

    @Override
    public void setMaxStackSize(int size) {
        this.maxStack = size;
    }

    @Override
    public int bridge$getBurnDuration(net.minecraft.world.item.ItemStack stack) {
        return this.m_7743_(stack);
    }

    @Override
    public boolean bridge$isLit() {
        return this.m_58425_();
    }

    public Object2IntOpenHashMap<ResourceLocation> getRecipesUsed() {
        return this.f_58320_;
    }
}

