/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.vehicle.AbstractMinecartContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.vehicle;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.mixin.core.world.entity.vehicle.AbstractMinecartMixin;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AbstractMinecartContainer.class})
public abstract class AbstractMinecartContainerMixin
extends AbstractMinecartMixin
implements IInventoryBridge,
Container {
    @Shadow
    private NonNullList<ItemStack> f_38202_;
    public List<HumanEntity> transaction;
    private int maxStack;

    @Inject(method={"<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<?> type, Level world, CallbackInfo ci) {
        this.f_38202_ = NonNullList.m_122780_((int)this.m_6643_(), (Object)ItemStack.f_41583_);
        this.maxStack = 64;
        this.transaction = new ArrayList<HumanEntity>();
    }

    @Inject(method={"<init>(Lnet/minecraft/world/entity/EntityType;DDDLnet/minecraft/world/level/Level;)V"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<?> type, double x, double y, double z, Level world, CallbackInfo ci) {
        this.f_38202_ = NonNullList.m_122780_((int)this.m_6643_(), (Object)ItemStack.f_41583_);
        this.maxStack = 64;
        this.transaction = new ArrayList<HumanEntity>();
    }

    @Override
    public List<ItemStack> getContents() {
        return this.f_38202_;
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
    public InventoryHolder getOwner() {
        CraftEntity cart = this.getBukkitEntity();
        if (cart instanceof InventoryHolder) {
            return (InventoryHolder)((Object)cart);
        }
        return null;
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
        return this.getBukkitEntity().getLocation();
    }

    @Override
    public Recipe<?> getCurrentRecipe() {
        return null;
    }

    @Override
    public void setCurrentRecipe(Recipe<?> recipe) {
    }
}

