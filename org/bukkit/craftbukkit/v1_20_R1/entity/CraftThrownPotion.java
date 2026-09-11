/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.alchemy.PotionUtils
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftThrowableProjectile;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class CraftThrownPotion
extends CraftThrowableProjectile
implements org.bukkit.entity.ThrownPotion {
    public CraftThrownPotion(CraftServer server, ThrownPotion entity) {
        super(server, (ThrowableItemProjectile)entity);
    }

    @Override
    public Collection<PotionEffect> getEffects() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (MobEffectInstance effect : PotionUtils.m_43547_((net.minecraft.world.item.ItemStack)this.getHandle().m_37454_())) {
            builder.add((Object)CraftPotionUtil.toBukkit(effect));
        }
        return builder.build();
    }

    @Override
    public ItemStack getItem() {
        return CraftItemStack.asBukkitCopy(this.getHandle().m_37454_());
    }

    @Override
    public void setItem(ItemStack item) {
        Preconditions.checkArgument((item != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null");
        Preconditions.checkArgument((item.getType() == Material.LINGERING_POTION || item.getType() == Material.SPLASH_POTION ? 1 : 0) != 0, (String)"ItemStack material must be Material.LINGERING_POTION or Material.SPLASH_POTION but was Material.%s", (Object)item.getType());
        this.getHandle().m_37446_(CraftItemStack.asNMSCopy(item));
    }

    public ThrownPotion getHandle() {
        return (ThrownPotion)this.entity;
    }
}

