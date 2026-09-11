/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.NonNullList
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.SlotAccess
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ClickAction
 *  net.minecraft.world.inventory.ClickType
 *  net.minecraft.world.inventory.ContainerSynchronizer
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraft.world.inventory.Slot
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.ContainerBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.SlotBridge;
import io.izzel.arclight.common.mod.server.ArclightContainer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ContainerSynchronizer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={AbstractContainerMenu.class})
public abstract class AbstractContainerMenuMixin
implements ContainerBridge {
    @Shadow
    private int f_38846_;
    @Shadow
    private int f_38845_;
    @Shadow
    @Final
    private Set<Slot> f_38847_;
    @Shadow
    @Final
    public int f_38840_;
    @Shadow
    @Final
    @javax.annotation.Nullable
    private MenuType<?> f_38843_;
    @Shadow
    private net.minecraft.world.item.ItemStack f_150396_;
    @Shadow
    @javax.annotation.Nullable
    private ContainerSynchronizer f_150397_;
    @Shadow
    public NonNullList<Slot> f_38839_;
    public boolean checkReachable = true;
    private InventoryView bukkitView;
    private Component title;

    @Shadow
    public void m_38946_() {
    }

    @Shadow
    protected abstract void m_38951_();

    @Shadow
    public abstract boolean m_5622_(Slot var1);

    @Shadow
    public abstract net.minecraft.world.item.ItemStack m_7648_(Player var1, int var2);

    @Shadow
    public abstract boolean m_5882_(net.minecraft.world.item.ItemStack var1, Slot var2);

    @Shadow
    public abstract Slot m_38853_(int var1);

    @Shadow
    public static int m_38947_(int clickedButton) {
        return 0;
    }

    @Shadow
    public static int m_38928_(int eventButton) {
        return 0;
    }

    @Shadow
    public static boolean m_38862_(int dragModeIn, Player player) {
        return false;
    }

    @Shadow
    public static boolean m_38899_(@Nullable Slot slotIn, net.minecraft.world.item.ItemStack stack, boolean stackSizeMatters) {
        return false;
    }

    @Shadow
    protected abstract boolean m_38903_(net.minecraft.world.item.ItemStack var1, int var2, int var3, boolean var4);

    @Shadow
    public abstract net.minecraft.world.item.ItemStack m_142621_();

    @Shadow
    public abstract void m_142503_(net.minecraft.world.item.ItemStack var1);

    @Shadow
    protected abstract SlotAccess m_150446_();

    @Shadow
    public abstract void m_150429_();

    @Shadow
    public abstract int m_182425_();

    @Shadow
    protected abstract boolean m_246200_(Player var1, ClickAction var2, Slot var3, net.minecraft.world.item.ItemStack var4, net.minecraft.world.item.ItemStack var5);

    public InventoryView getBukkitView() {
        if (this.bukkitView == null) {
            this.bukkitView = ArclightContainer.createInvView((AbstractContainerMenu)this);
        }
        return this.bukkitView;
    }

    public void transferTo(AbstractContainerMenu other, CraftHumanEntity player) {
        InventoryView source = this.getBukkitView();
        InventoryView destination = ((ContainerBridge)other).bridge$getBukkitView();
        ((IInventoryBridge)((CraftInventory)source.getTopInventory()).getInventory()).onClose(player);
        ((IInventoryBridge)((CraftInventory)source.getBottomInventory()).getInventory()).onClose(player);
        ((IInventoryBridge)((CraftInventory)destination.getTopInventory()).getInventory()).onOpen(player);
        ((IInventoryBridge)((CraftInventory)destination.getBottomInventory()).getInventory()).onOpen(player);
    }

    public Component getTitle() {
        if (this.title == null) {
            if (this.f_38843_ != null) {
                ResourceLocation key = ForgeRegistries.MENU_TYPES.getKey(this.f_38843_);
                return Component.m_237115_((String)Optional.ofNullable(key).map(Object::toString).orElseGet(this::toString));
            }
            return Component.m_237115_((String)this.toString());
        }
        return this.title;
    }

    public void setTitle(Component title) {
        if (this.title == null) {
            this.title = title == null ? this.getTitle() : title;
        }
    }

    public void broadcastCarriedItem() {
        this.f_150396_ = this.m_142621_().m_41777_();
        if (this.f_150397_ != null) {
            this.f_150397_.m_142529_((AbstractContainerMenu)this, this.f_150396_);
        }
    }

    @Overwrite
    private void m_150430_(int slotId, int dragType, ClickType clickType, Player player) {
        block43: {
            block55: {
                block51: {
                    net.minecraft.world.item.ItemStack itemstack7;
                    net.minecraft.world.item.ItemStack itemstack4;
                    Slot slot2;
                    Inventory inventory;
                    block54: {
                        block53: {
                            block52: {
                                block48: {
                                    ClickAction clickaction;
                                    block50: {
                                        block49: {
                                            block47: {
                                                block41: {
                                                    block46: {
                                                        net.minecraft.world.item.ItemStack itemstack12;
                                                        block45: {
                                                            block44: {
                                                                block42: {
                                                                    inventory = player.m_150109_();
                                                                    if (clickType != ClickType.QUICK_CRAFT) break block41;
                                                                    int j1 = this.f_38846_;
                                                                    this.f_38846_ = AbstractContainerMenuMixin.m_38947_(dragType);
                                                                    if (j1 == 1 && this.f_38846_ == 2 || j1 == this.f_38846_) break block42;
                                                                    this.m_38951_();
                                                                    break block43;
                                                                }
                                                                if (!this.m_142621_().m_41619_()) break block44;
                                                                this.m_38951_();
                                                                break block43;
                                                            }
                                                            if (this.f_38846_ != 0) break block45;
                                                            this.f_38845_ = AbstractContainerMenuMixin.m_38928_(dragType);
                                                            if (AbstractContainerMenuMixin.m_38862_(this.f_38845_, player)) {
                                                                this.f_38846_ = 1;
                                                                this.f_38847_.clear();
                                                            } else {
                                                                this.m_38951_();
                                                            }
                                                            break block43;
                                                        }
                                                        if (this.f_38846_ != 1) break block46;
                                                        Slot slot7 = (Slot)this.f_38839_.get(slotId);
                                                        if (!AbstractContainerMenuMixin.m_38899_(slot7, itemstack12 = this.m_142621_(), true) || !slot7.m_5857_(itemstack12) || this.f_38845_ != 2 && itemstack12.m_41613_() <= this.f_38847_.size() || !this.m_5622_(slot7)) break block43;
                                                        this.f_38847_.add(slot7);
                                                        break block43;
                                                    }
                                                    if (this.f_38846_ == 2) {
                                                        if (!this.f_38847_.isEmpty()) {
                                                            boolean needsUpdate;
                                                            net.minecraft.world.item.ItemStack itemstack9 = this.m_142621_().m_41777_();
                                                            if (itemstack9.m_41619_()) {
                                                                this.m_38951_();
                                                                return;
                                                            }
                                                            int k1 = this.m_142621_().m_41613_();
                                                            HashMap<Integer, net.minecraft.world.item.ItemStack> draggedSlots = new HashMap<Integer, net.minecraft.world.item.ItemStack>();
                                                            for (Slot slot8 : this.f_38847_) {
                                                                net.minecraft.world.item.ItemStack itemstack13 = this.m_142621_();
                                                                if (slot8 == null || !AbstractContainerMenuMixin.m_38899_(slot8, itemstack13, true) || !slot8.m_5857_(itemstack13) || this.f_38845_ != 2 && itemstack13.m_41613_() < this.f_38847_.size() || !this.m_5622_(slot8)) continue;
                                                                int j3 = slot8.m_6657_() ? slot8.m_7993_().m_41613_() : 0;
                                                                int k3 = Math.min(itemstack9.m_41741_(), slot8.m_5866_(itemstack9));
                                                                int l3 = Math.min(AbstractContainerMenu.m_278794_(this.f_38847_, (int)this.f_38845_, (net.minecraft.world.item.ItemStack)itemstack9) + j3, k3);
                                                                k1 -= l3 - j3;
                                                                draggedSlots.put(slot8.f_40219_, itemstack9.m_255036_(l3));
                                                            }
                                                            InventoryView view = this.getBukkitView();
                                                            CraftItemStack newcursor = CraftItemStack.asCraftMirror(itemstack9);
                                                            ((ItemStack)newcursor).setAmount(k1);
                                                            HashMap<Integer, ItemStack> eventmap = new HashMap<Integer, ItemStack>();
                                                            for (Map.Entry ditem : draggedSlots.entrySet()) {
                                                                eventmap.put((Integer)ditem.getKey(), CraftItemStack.asBukkitCopy((net.minecraft.world.item.ItemStack)ditem.getValue()));
                                                            }
                                                            net.minecraft.world.item.ItemStack oldCursor = this.m_142621_();
                                                            this.m_142503_(CraftItemStack.asNMSCopy(newcursor));
                                                            InventoryDragEvent event = new InventoryDragEvent(view, ((ItemStack)newcursor).getType() != Material.AIR ? newcursor : null, CraftItemStack.asBukkitCopy(oldCursor), this.f_38845_ == 1, eventmap);
                                                            Bukkit.getPluginManager().callEvent(event);
                                                            boolean bl = needsUpdate = event.getResult() != Event.Result.DEFAULT;
                                                            if (event.getResult() != Event.Result.DENY) {
                                                                for (Map.Entry dslot : draggedSlots.entrySet()) {
                                                                    view.setItem((Integer)dslot.getKey(), CraftItemStack.asBukkitCopy((net.minecraft.world.item.ItemStack)dslot.getValue()));
                                                                }
                                                                if (this.m_142621_() != null) {
                                                                    this.m_142503_(CraftItemStack.asNMSCopy(event.getCursor()));
                                                                    needsUpdate = true;
                                                                }
                                                            } else {
                                                                this.m_142503_(oldCursor);
                                                            }
                                                            if (needsUpdate && player instanceof ServerPlayer) {
                                                                this.m_150429_();
                                                            }
                                                        }
                                                        this.m_38951_();
                                                    } else {
                                                        this.m_38951_();
                                                    }
                                                    break block43;
                                                }
                                                if (this.f_38846_ == 0) break block47;
                                                this.m_38951_();
                                                break block43;
                                            }
                                            if (clickType != ClickType.PICKUP && clickType != ClickType.QUICK_MOVE || dragType != 0 && dragType != 1) break block48;
                                            ClickAction clickAction = clickaction = dragType == 0 ? ClickAction.PRIMARY : ClickAction.SECONDARY;
                                            if (slotId != -999) break block49;
                                            if (this.m_142621_().m_41619_()) break block43;
                                            if (clickaction == ClickAction.PRIMARY) {
                                                net.minecraft.world.item.ItemStack carried = this.m_142621_();
                                                this.m_142503_(net.minecraft.world.item.ItemStack.f_41583_);
                                                player.m_36176_(carried, true);
                                            } else {
                                                player.m_36176_(this.m_142621_().m_41620_(1), true);
                                            }
                                            break block43;
                                        }
                                        if (clickType != ClickType.QUICK_MOVE) break block50;
                                        if (slotId < 0) {
                                            return;
                                        }
                                        Slot slot6 = (Slot)this.f_38839_.get(slotId);
                                        if (!slot6.m_8010_(player)) {
                                            return;
                                        }
                                        net.minecraft.world.item.ItemStack itemstack9 = this.m_7648_(player, slotId);
                                        while (!itemstack9.m_41619_() && net.minecraft.world.item.ItemStack.m_41656_((net.minecraft.world.item.ItemStack)slot6.m_7993_(), (net.minecraft.world.item.ItemStack)itemstack9)) {
                                            itemstack9 = this.m_7648_(player, slotId);
                                        }
                                        break block43;
                                    }
                                    if (slotId < 0) {
                                        return;
                                    }
                                    Slot slot7 = (Slot)this.f_38839_.get(slotId);
                                    net.minecraft.world.item.ItemStack itemstack10 = slot7.m_7993_();
                                    net.minecraft.world.item.ItemStack itemstack11 = this.m_142621_();
                                    player.m_141945_(itemstack11, slot7.m_7993_(), clickaction);
                                    if (!this.m_246200_(player, clickaction, slot7, itemstack10, itemstack11) && !ForgeHooks.onItemStackedOn((net.minecraft.world.item.ItemStack)itemstack10, (net.minecraft.world.item.ItemStack)itemstack11, (Slot)slot7, (ClickAction)clickaction, (Player)player, (SlotAccess)this.m_150446_())) {
                                        if (itemstack10.m_41619_()) {
                                            if (!itemstack11.m_41619_()) {
                                                int l2 = clickaction == ClickAction.PRIMARY ? itemstack11.m_41613_() : 1;
                                                this.m_142503_(slot7.m_150656_(itemstack11, l2));
                                            }
                                        } else if (slot7.m_8010_(player)) {
                                            if (itemstack11.m_41619_()) {
                                                int i3 = clickaction == ClickAction.PRIMARY ? itemstack10.m_41613_() : (itemstack10.m_41613_() + 1) / 2;
                                                Optional optional1 = slot7.m_150641_(i3, Integer.MAX_VALUE, player);
                                                optional1.ifPresent(p_150421_ -> {
                                                    this.m_142503_((net.minecraft.world.item.ItemStack)p_150421_);
                                                    slot7.m_142406_(player, p_150421_);
                                                });
                                            } else if (slot7.m_5857_(itemstack11)) {
                                                if (net.minecraft.world.item.ItemStack.m_150942_((net.minecraft.world.item.ItemStack)itemstack10, (net.minecraft.world.item.ItemStack)itemstack11)) {
                                                    int j3 = clickaction == ClickAction.PRIMARY ? itemstack11.m_41613_() : 1;
                                                    this.m_142503_(slot7.m_150656_(itemstack11, j3));
                                                } else if (itemstack11.m_41613_() <= slot7.m_5866_(itemstack11)) {
                                                    this.m_142503_(itemstack10);
                                                    slot7.m_269060_(itemstack11);
                                                }
                                            } else if (net.minecraft.world.item.ItemStack.m_150942_((net.minecraft.world.item.ItemStack)itemstack10, (net.minecraft.world.item.ItemStack)itemstack11)) {
                                                Optional optional = slot7.m_150641_(itemstack10.m_41613_(), itemstack11.m_41741_() - itemstack11.m_41613_(), player);
                                                optional.ifPresent(p_150428_ -> {
                                                    itemstack11.m_41769_(p_150428_.m_41613_());
                                                    slot7.m_142406_(player, p_150428_);
                                                });
                                            }
                                        }
                                    }
                                    slot7.m_6654_();
                                    if (!(player instanceof ServerPlayer) || slot7.m_6641_() == 64) break block43;
                                    ((ServerPlayer)player).f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(this.f_38840_, this.m_182425_(), slot7.f_40219_, slot7.m_7993_()));
                                    if (this.getBukkitView().getType() == InventoryType.WORKBENCH || this.getBukkitView().getType() == InventoryType.CRAFTING) {
                                        ((ServerPlayer)player).f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(this.f_38840_, this.m_182425_(), 0, this.m_38853_(0).m_7993_()));
                                    }
                                    break block43;
                                }
                                if (clickType != ClickType.SWAP) break block51;
                                slot2 = (Slot)this.f_38839_.get(slotId);
                                itemstack4 = inventory.m_8020_(dragType);
                                itemstack7 = slot2.m_7993_();
                                if (itemstack4.m_41619_() && itemstack7.m_41619_()) break block43;
                                if (!itemstack4.m_41619_()) break block52;
                                if (!slot2.m_8010_(player)) break block43;
                                inventory.m_6836_(dragType, itemstack7);
                                ((SlotBridge)slot2).bridge$onSwapCraft(itemstack7.m_41613_());
                                slot2.m_269060_(net.minecraft.world.item.ItemStack.f_41583_);
                                slot2.m_142406_(player, itemstack7);
                                break block43;
                            }
                            if (!itemstack7.m_41619_()) break block53;
                            if (!slot2.m_5857_(itemstack4)) break block43;
                            int l1 = slot2.m_5866_(itemstack4);
                            if (itemstack4.m_41613_() > l1) {
                                slot2.m_269060_(itemstack4.m_41620_(l1));
                            } else {
                                inventory.m_6836_(dragType, net.minecraft.world.item.ItemStack.f_41583_);
                                slot2.m_269060_(itemstack4);
                            }
                            break block43;
                        }
                        if (!slot2.m_8010_(player) || !slot2.m_5857_(itemstack4)) break block43;
                        int i2 = slot2.m_5866_(itemstack4);
                        if (itemstack4.m_41613_() <= i2) break block54;
                        slot2.m_269060_(itemstack4.m_41620_(i2));
                        slot2.m_142406_(player, itemstack7);
                        if (inventory.m_36054_(itemstack7)) break block43;
                        player.m_36176_(itemstack7, true);
                        break block43;
                    }
                    inventory.m_6836_(dragType, itemstack7);
                    slot2.m_269060_(itemstack4);
                    slot2.m_142406_(player, itemstack7);
                    break block43;
                }
                if (clickType != ClickType.CLONE || !player.m_150110_().f_35937_ || !this.m_142621_().m_41619_() || slotId < 0) break block55;
                Slot slot5 = (Slot)this.f_38839_.get(slotId);
                if (!slot5.m_6657_()) break block43;
                net.minecraft.world.item.ItemStack itemstack6 = slot5.m_7993_();
                this.m_142503_(itemstack6.m_255036_(itemstack6.m_41741_()));
                break block43;
            }
            if (clickType == ClickType.THROW && this.m_142621_().m_41619_() && slotId >= 0) {
                Slot slot4 = (Slot)this.f_38839_.get(slotId);
                int i1 = dragType == 0 ? 1 : slot4.m_7993_().m_41613_();
                net.minecraft.world.item.ItemStack itemstack8 = slot4.m_150647_(i1, Integer.MAX_VALUE, player);
                player.m_36176_(itemstack8, true);
            } else if (clickType == ClickType.PICKUP_ALL && slotId >= 0) {
                Slot slot3 = (Slot)this.f_38839_.get(slotId);
                net.minecraft.world.item.ItemStack itemstack5 = this.m_142621_();
                if (!(itemstack5.m_41619_() || slot3.m_6657_() && slot3.m_8010_(player))) {
                    int k1 = dragType == 0 ? 0 : this.f_38839_.size() - 1;
                    int j2 = dragType == 0 ? 1 : -1;
                    for (int k2 = 0; k2 < 2; ++k2) {
                        for (int k3 = k1; k3 >= 0 && k3 < this.f_38839_.size() && itemstack5.m_41613_() < itemstack5.m_41741_(); k3 += j2) {
                            Slot slot8 = (Slot)this.f_38839_.get(k3);
                            if (!slot8.m_6657_() || !AbstractContainerMenuMixin.m_38899_(slot8, itemstack5, true) || !slot8.m_8010_(player) || !this.m_5882_(itemstack5, slot8)) continue;
                            net.minecraft.world.item.ItemStack itemstack12 = slot8.m_7993_();
                            if (k2 == 0 && itemstack12.m_41613_() == itemstack12.m_41741_()) continue;
                            net.minecraft.world.item.ItemStack itemstack13 = slot8.m_150647_(itemstack12.m_41613_(), itemstack5.m_41741_() - itemstack5.m_41613_(), player);
                            itemstack5.m_41769_(itemstack13.m_41613_());
                        }
                    }
                }
            }
        }
    }

    @Overwrite
    public void m_6877_(Player player) {
        net.minecraft.world.item.ItemStack itemstack;
        if (player instanceof ServerPlayer && !(itemstack = this.m_142621_()).m_41619_()) {
            this.m_142503_(net.minecraft.world.item.ItemStack.f_41583_);
            if (player.m_6084_() && !((ServerPlayer)player).m_9232_()) {
                player.m_150109_().m_150079_(itemstack);
            } else {
                player.m_36176_(itemstack, false);
            }
        }
    }

    @Override
    public boolean bridge$isCheckReachable() {
        return this.checkReachable;
    }

    @Override
    public InventoryView bridge$getBukkitView() {
        return this.getBukkitView();
    }

    @Override
    public void bridge$transferTo(AbstractContainerMenu other, CraftHumanEntity player) {
        this.transferTo(other, player);
    }

    @Override
    public Component bridge$getTitle() {
        return this.getTitle();
    }

    @Override
    public void bridge$setTitle(Component title) {
        this.setTitle(title);
    }
}

