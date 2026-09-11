/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.PlayerEnderChestContainer
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.EnderChestBlockEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.mixin.core.world.SimpleContainerMixin;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={PlayerEnderChestContainer.class})
public abstract class EnderChestInventoryMixin
extends SimpleContainerMixin
implements IInventoryBridge,
Container {
    @Shadow
    private EnderChestBlockEntity f_40101_;
    private Player owner;

    public void arclight$constructor$super(int numSlots, InventoryHolder owner) {
        throw new RuntimeException();
    }

    public void arclight$constructor(Player owner) {
        this.arclight$constructor$super(27, ((PlayerEntityBridge)owner).bridge$getBukkitEntity());
        this.owner = owner;
    }

    public InventoryHolder getBukkitOwner() {
        return ((PlayerEntityBridge)this.owner).bridge$getBukkitEntity();
    }

    @Override
    public InventoryHolder getOwner() {
        return ((PlayerEntityBridge)this.owner).bridge$getBukkitEntity();
    }

    @Override
    public void setOwner(InventoryHolder owner) {
        if (owner instanceof HumanEntity) {
            this.owner = ((CraftHumanEntity)owner).getHandle();
        }
    }

    @Override
    public Location getLocation() {
        return CraftBlock.at((LevelAccessor)this.f_40101_.m_58904_(), this.f_40101_.m_58899_()).getLocation();
    }
}

