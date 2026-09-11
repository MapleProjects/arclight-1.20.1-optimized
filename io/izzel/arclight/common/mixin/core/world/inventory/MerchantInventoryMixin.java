/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.inventory.MerchantContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.trading.Merchant
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.trading.Merchant;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractVillager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={MerchantContainer.class})
public abstract class MerchantInventoryMixin
implements IInventoryBridge,
Container {
    @Shadow
    @Final
    private NonNullList<ItemStack> f_39998_;
    @Shadow
    @Final
    private Merchant f_39997_;
    private List<HumanEntity> transactions = new ArrayList<HumanEntity>();
    private int maxStack = 64;

    @Override
    public List<ItemStack> getContents() {
        return this.f_39998_;
    }

    @Override
    public void onOpen(CraftHumanEntity who) {
        this.transactions.add(who);
    }

    @Override
    public void onClose(CraftHumanEntity who) {
        this.transactions.remove(who);
        this.f_39997_.m_7189_(null);
    }

    @Override
    public List<HumanEntity> getViewers() {
        return this.transactions;
    }

    @Override
    public InventoryHolder getOwner() {
        return this.f_39997_ instanceof AbstractVillager ? (CraftAbstractVillager)((EntityBridge)this.f_39997_).bridge$getBukkitEntity() : null;
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
    public Location getLocation() {
        return this.f_39997_ instanceof AbstractVillager ? ((EntityBridge)this.f_39997_).bridge$getBukkitEntity().getLocation() : null;
    }

    @Override
    public Recipe<?> getCurrentRecipe() {
        return null;
    }

    @Override
    public void setCurrentRecipe(Recipe<?> recipe) {
    }
}

