/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.inventory.DataSlot
 *  net.minecraft.world.inventory.EnchantmentMenu
 *  net.minecraft.world.item.EnchantedBookItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.item.enchantment.EnchantmentInstance
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.EnchantmentTableBlock
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.PosContainerBridge;
import io.izzel.arclight.common.bridge.core.util.IWorldPosCallableBridge;
import io.izzel.arclight.common.mixin.core.world.inventory.AbstractContainerMenuMixin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryEnchanting;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EnchantmentMenu.class}, priority=39)
public abstract class EnchantmentContainerMixin
extends AbstractContainerMenuMixin
implements PosContainerBridge {
    @Shadow
    @Final
    private Container f_39449_;
    @Shadow
    @Final
    private ContainerLevelAccess f_39450_;
    @Shadow
    @Final
    private RandomSource f_39451_;
    @Shadow
    @Final
    private DataSlot f_39452_;
    @Shadow
    @Final
    public int[] f_39446_;
    @Shadow
    @Final
    public int[] f_39447_;
    @Shadow
    @Final
    public int[] f_39448_;
    private CraftInventoryView bukkitEntity = null;
    private Inventory playerInventory;

    @Shadow
    protected abstract List<EnchantmentInstance> m_39471_(ItemStack var1, int var2, int var3);

    @Inject(method={"<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V"}, at={@At(value="RETURN")})
    public void arclight$init(int id, Inventory playerInventory, ContainerLevelAccess worldPosCallable, CallbackInfo ci) {
        this.playerInventory = playerInventory;
    }

    @Inject(method={"stillValid"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$unreachable(Player playerIn, CallbackInfoReturnable<Boolean> cir) {
        if (!this.bridge$isCheckReachable()) {
            cir.setReturnValue((Object)true);
        }
    }

    @Overwrite
    public void m_6199_(Container inventoryIn) {
        if (inventoryIn == this.f_39449_) {
            ItemStack itemstack = inventoryIn.m_8020_(0);
            if (!itemstack.m_41619_()) {
                boolean enchantable = itemstack.m_41792_();
                this.f_39450_.m_39292_((p_217002_2_, p_217002_3_) -> {
                    float power = 0.0f;
                    for (BlockPos blockpos : EnchantmentTableBlock.f_207902_) {
                        if (!EnchantmentTableBlock.m_207909_((Level)p_217002_2_, (BlockPos)p_217002_3_, (BlockPos)blockpos)) continue;
                        power += p_217002_2_.m_8055_(p_217002_3_.m_121955_((Vec3i)blockpos)).getEnchantPowerBonus((LevelReader)p_217002_2_, p_217002_3_.m_121955_((Vec3i)blockpos));
                    }
                    this.f_39451_.m_188584_((long)this.f_39452_.m_6501_());
                    for (int i1 = 0; i1 < 3; ++i1) {
                        this.f_39446_[i1] = EnchantmentHelper.m_220287_((RandomSource)this.f_39451_, (int)i1, (int)((int)power), (ItemStack)itemstack);
                        this.f_39447_[i1] = -1;
                        this.f_39448_[i1] = -1;
                        if (this.f_39446_[i1] < i1 + 1) {
                            this.f_39446_[i1] = 0;
                        }
                        this.f_39446_[i1] = ForgeEventFactory.onEnchantmentLevelSet((Level)p_217002_2_, (BlockPos)p_217002_3_, (int)i1, (int)((int)power), (ItemStack)itemstack, (int)this.f_39446_[i1]);
                    }
                    for (int j1 = 0; j1 < 3; ++j1) {
                        List<EnchantmentInstance> list;
                        if (this.f_39446_[j1] <= 0 || (list = this.m_39471_(itemstack, j1, this.f_39446_[j1])) == null || list.isEmpty()) continue;
                        EnchantmentInstance enchantmentdata = list.get(this.f_39451_.m_188503_(list.size()));
                        this.f_39447_[j1] = BuiltInRegistries.f_256876_.m_7447_((Object)enchantmentdata.f_44947_);
                        this.f_39448_[j1] = enchantmentdata.f_44948_;
                    }
                    CraftItemStack item = CraftItemStack.asCraftMirror(itemstack);
                    EnchantmentOffer[] offers = new EnchantmentOffer[3];
                    for (int j = 0; j < 3; ++j) {
                        org.bukkit.enchantments.Enchantment enchantment = this.f_39447_[j] >= 0 ? org.bukkit.enchantments.Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(ForgeRegistries.ENCHANTMENTS.getKey((Object)((Enchantment)BuiltInRegistries.f_256876_.m_7942_(this.f_39447_[j]))))) : null;
                        offers[j] = enchantment != null ? new EnchantmentOffer(enchantment, this.f_39448_[j], this.f_39446_[j]) : null;
                    }
                    PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(((ServerPlayerEntityBridge)this.playerInventory.f_35978_).bridge$getBukkitEntity(), this.getBukkitView(), ((IWorldPosCallableBridge)this.f_39450_).bridge$getLocation().getBlock(), item, offers, (int)power);
                    event.setCancelled(!enchantable);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) {
                        for (int j = 0; j < 3; ++j) {
                            this.f_39446_[j] = 0;
                            this.f_39447_[j] = -1;
                            this.f_39448_[j] = -1;
                        }
                        return;
                    }
                    for (int j = 0; j < 3; ++j) {
                        EnchantmentOffer offer = event.getOffers()[j];
                        if (offer != null) {
                            this.f_39446_[j] = offer.getCost();
                            this.f_39447_[j] = BuiltInRegistries.f_256876_.m_7447_((Object)((Enchantment)ForgeRegistries.ENCHANTMENTS.getValue(CraftNamespacedKey.toMinecraft(offer.getEnchantment().getKey()))));
                            this.f_39448_[j] = offer.getEnchantmentLevel();
                            continue;
                        }
                        this.f_39446_[j] = 0;
                        this.f_39447_[j] = -1;
                        this.f_39448_[j] = -1;
                    }
                    this.m_38946_();
                });
            } else {
                for (int i = 0; i < 3; ++i) {
                    this.f_39446_[i] = 0;
                    this.f_39447_[i] = -1;
                    this.f_39448_[i] = -1;
                }
            }
        }
    }

    @Overwrite
    public boolean m_6366_(Player playerIn, int id) {
        ItemStack itemstack = this.f_39449_.m_8020_(0);
        ItemStack itemstack1 = this.f_39449_.m_8020_(1);
        int i = id + 1;
        if ((itemstack1.m_41619_() || itemstack1.m_41613_() < i) && !playerIn.m_150110_().f_35937_) {
            return false;
        }
        if (this.f_39446_[id] <= 0 || itemstack.m_41619_() || (playerIn.f_36078_ < i || playerIn.f_36078_ < this.f_39446_[id]) && !playerIn.m_150110_().f_35937_) {
            return false;
        }
        this.f_39450_.m_39292_((p_217003_6_, p_217003_7_) -> {
            ItemStack itemstack2 = itemstack;
            List<EnchantmentInstance> list = this.m_39471_(itemstack, id, this.f_39446_[id]);
            boolean flag = itemstack.m_41720_() == Items.f_42517_;
            HashMap<org.bukkit.enchantments.Enchantment, Integer> enchants = new HashMap<org.bukkit.enchantments.Enchantment, Integer>();
            for (EnchantmentInstance obj : list) {
                enchants.put(org.bukkit.enchantments.Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(ForgeRegistries.ENCHANTMENTS.getKey((Object)obj.f_44947_))), obj.f_44948_);
            }
            CraftItemStack item = CraftItemStack.asCraftMirror(itemstack2);
            org.bukkit.enchantments.Enchantment hintedEnchantment = org.bukkit.enchantments.Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.f_256876_.m_7981_((Object)Enchantment.m_44697_((int)this.f_39447_[id]))));
            int hintedEnchantmentLevel = this.f_39448_[id];
            EnchantItemEvent event = new EnchantItemEvent((org.bukkit.entity.Player)((Object)((PlayerEntityBridge)playerIn).bridge$getBukkitEntity()), this.getBukkitView(), ((IWorldPosCallableBridge)this.f_39450_).bridge$getLocation().getBlock(), item, this.f_39446_[id], enchants, hintedEnchantment, hintedEnchantmentLevel, id);
            Bukkit.getPluginManager().callEvent(event);
            int level = event.getExpLevelCost();
            if (event.isCancelled() || level > playerIn.f_36078_ && !playerIn.m_150110_().f_35937_ || event.getEnchantsToAdd().isEmpty()) {
                return;
            }
            if (flag) {
                itemstack2 = new ItemStack((ItemLike)Items.f_42690_);
                CompoundTag tag = itemstack2.m_41783_();
                if (tag != null) {
                    itemstack2.m_41751_(tag.m_6426_());
                }
                this.f_39449_.m_6836_(0, itemstack2);
            }
            for (Map.Entry<org.bukkit.enchantments.Enchantment, Integer> entry : event.getEnchantsToAdd().entrySet()) {
                try {
                    if (flag) {
                        NamespacedKey enchantId = entry.getKey().getKey();
                        Enchantment nms = (Enchantment)ForgeRegistries.ENCHANTMENTS.getValue(CraftNamespacedKey.toMinecraft(enchantId));
                        if (nms == null) continue;
                        EnchantmentInstance weightedrandomenchant = new EnchantmentInstance(nms, entry.getValue().intValue());
                        EnchantedBookItem.m_41153_((ItemStack)itemstack2, (EnchantmentInstance)weightedrandomenchant);
                        continue;
                    }
                    item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
                }
                catch (IllegalArgumentException illegalArgumentException) {}
            }
            playerIn.m_7408_(itemstack, i);
            if (!playerIn.m_150110_().f_35937_) {
                itemstack1.m_41774_(i);
                if (itemstack1.m_41619_()) {
                    this.f_39449_.m_6836_(1, ItemStack.f_41583_);
                }
            }
            playerIn.m_36220_(Stats.f_12964_);
            if (playerIn instanceof ServerPlayer) {
                CriteriaTriggers.f_10575_.m_27668_((ServerPlayer)playerIn, itemstack2, i);
            }
            this.f_39449_.m_6596_();
            this.f_39452_.m_6422_(playerIn.m_36322_());
            this.m_6199_(this.f_39449_);
            p_217003_6_.m_5594_(null, p_217003_7_, SoundEvents.f_11887_, SoundSource.BLOCKS, 1.0f, p_217003_6_.f_46441_.m_188501_() * 0.1f + 0.9f);
        });
        return true;
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (this.bukkitEntity != null) {
            return this.bukkitEntity;
        }
        CraftInventoryEnchanting inventory = new CraftInventoryEnchanting(this.f_39449_);
        this.bukkitEntity = new CraftInventoryView(((PlayerEntityBridge)this.playerInventory.f_35978_).bridge$getBukkitEntity(), inventory, (AbstractContainerMenu)this);
        return this.bukkitEntity;
    }

    @Override
    public ContainerLevelAccess bridge$getWorldPos() {
        return this.f_39450_;
    }
}

