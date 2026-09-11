/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Fox
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ChorusFruitItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={ChorusFruitItem.class})
public class ChorusFruitItemMixin
extends Item {
    public ChorusFruitItemMixin(Item.Properties properties) {
        super(properties);
    }

    @Overwrite
    @NotNull
    public ItemStack m_5922_(@NotNull ItemStack stack, @NotNull Level worldIn, @NotNull LivingEntity entityLiving) {
        ItemStack itemstack = super.m_5922_(stack, worldIn, entityLiving);
        if (!worldIn.f_46443_) {
            double d0 = entityLiving.m_20185_();
            double d1 = entityLiving.m_20186_();
            double d2 = entityLiving.m_20189_();
            for (int i = 0; i < 16; ++i) {
                Object event;
                double d3 = entityLiving.m_20185_() + (entityLiving.m_217043_().m_188500_() - 0.5) * 16.0;
                double d4 = Mth.m_14008_((double)(entityLiving.m_20186_() + (double)(entityLiving.m_217043_().m_188503_(16) - 8)), (double)0.0, (double)(worldIn.m_141928_() - 1));
                double d5 = entityLiving.m_20189_() + (entityLiving.m_217043_().m_188500_() - 0.5) * 16.0;
                if (entityLiving instanceof ServerPlayer && DistValidate.isValid((LevelAccessor)worldIn)) {
                    CraftPlayer player = ((ServerPlayerEntityBridge)entityLiving).bridge$getBukkitEntity();
                    event = new PlayerTeleportEvent(player, player.getLocation(), new Location(player.getWorld(), d3, d4, d5), PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT);
                    Bukkit.getPluginManager().callEvent((Event)event);
                    if (((PlayerMoveEvent)event).isCancelled()) break;
                    d3 = ((PlayerMoveEvent)event).getTo().getX();
                    d4 = ((PlayerMoveEvent)event).getTo().getY();
                    d5 = ((PlayerMoveEvent)event).getTo().getZ();
                }
                if (entityLiving.m_20159_()) {
                    entityLiving.m_8127_();
                }
                Vec3 vec3d = entityLiving.m_20182_();
                event = ForgeEventFactory.onChorusFruitTeleport((LivingEntity)entityLiving, (double)d3, (double)d4, (double)d5);
                if (event.isCanceled()) {
                    return itemstack;
                }
                if (!entityLiving.m_20984_(d3, d4, d5, true)) continue;
                worldIn.m_214171_(GameEvent.f_238175_, vec3d, GameEvent.Context.m_223717_((Entity)entityLiving));
                SoundEvent soundevent = entityLiving instanceof Fox ? SoundEvents.f_11953_ : SoundEvents.f_11757_;
                worldIn.m_6263_(null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0f, 1.0f);
                entityLiving.m_5496_(soundevent, 1.0f, 1.0f);
                break;
            }
            if (entityLiving instanceof Player) {
                ((Player)entityLiving).m_36335_().m_41524_((Item)this, 20);
            }
        }
        return itemstack;
    }
}

