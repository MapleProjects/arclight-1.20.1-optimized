/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.monster.ZombieVillager
 *  net.minecraft.world.entity.npc.VillagerProfession
 *  net.minecraft.world.entity.npc.VillagerType
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.Locale;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.entity.EntityPotionEffectEvent;

public class CraftVillagerZombie
extends CraftZombie
implements ZombieVillager {
    public CraftVillagerZombie(CraftServer server, net.minecraft.world.entity.monster.ZombieVillager entity) {
        super(server, (Zombie)entity);
    }

    public net.minecraft.world.entity.monster.ZombieVillager getHandle() {
        return (net.minecraft.world.entity.monster.ZombieVillager)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftVillagerZombie";
    }

    @Override
    public Villager.Profession getVillagerProfession() {
        return Villager.Profession.valueOf(BuiltInRegistries.f_256735_.m_7981_((Object)this.getHandle().m_7141_().m_35571_()).m_135815_().toUpperCase(Locale.ROOT));
    }

    @Override
    public void setVillagerProfession(Villager.Profession profession) {
        Preconditions.checkArgument((profession != null ? 1 : 0) != 0, (Object)"Villager.Profession cannot be null");
        this.getHandle().m_34375_(this.getHandle().m_7141_().m_35565_((VillagerProfession)BuiltInRegistries.f_256735_.m_7745_(new ResourceLocation(profession.name().toLowerCase(Locale.ROOT)))));
    }

    @Override
    public Villager.Type getVillagerType() {
        return Villager.Type.valueOf(BuiltInRegistries.f_256934_.m_7981_((Object)this.getHandle().m_7141_().m_35560_()).m_135815_().toUpperCase(Locale.ROOT));
    }

    @Override
    public void setVillagerType(Villager.Type type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Villager.Type cannot be null");
        this.getHandle().m_34375_(this.getHandle().m_7141_().m_35567_((VillagerType)BuiltInRegistries.f_256934_.m_7745_(CraftNamespacedKey.toMinecraft(type.getKey()))));
    }

    @Override
    public boolean isConverting() {
        return this.getHandle().m_34408_();
    }

    @Override
    public int getConversionTime() {
        Preconditions.checkState((boolean)this.isConverting(), (Object)"Entity not converting");
        return this.getHandle().f_34365_;
    }

    @Override
    public void setConversionTime(int time) {
        if (time < 0) {
            this.getHandle().f_34365_ = -1;
            this.getHandle().m_20088_().m_135381_(net.minecraft.world.entity.monster.ZombieVillager.f_34359_, (Object)false);
            this.getHandle().f_34360_ = null;
            this.getHandle().removeEffect(MobEffects.f_19600_, EntityPotionEffectEvent.Cause.CONVERSION);
        } else {
            this.getHandle().m_34383_(null, time);
        }
    }

    @Override
    public OfflinePlayer getConversionPlayer() {
        return this.getHandle().f_34360_ == null ? null : Bukkit.getOfflinePlayer(this.getHandle().f_34360_);
    }

    @Override
    public void setConversionPlayer(OfflinePlayer conversionPlayer) {
        if (!this.isConverting()) {
            return;
        }
        this.getHandle().f_34360_ = conversionPlayer == null ? null : conversionPlayer.getUniqueId();
    }
}

