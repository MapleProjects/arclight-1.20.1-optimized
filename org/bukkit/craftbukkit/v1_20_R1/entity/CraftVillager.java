/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.monster.ZombieVillager
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.entity.npc.VillagerProfession
 *  net.minecraft.world.entity.npc.VillagerType
 *  net.minecraft.world.level.block.BedBlock
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.Locale;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractVillager;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CraftVillager
extends CraftAbstractVillager
implements Villager {
    public CraftVillager(CraftServer server, net.minecraft.world.entity.npc.Villager entity) {
        super(server, (AbstractVillager)entity);
    }

    public net.minecraft.world.entity.npc.Villager getHandle() {
        return (net.minecraft.world.entity.npc.Villager)this.entity;
    }

    @Override
    public String toString() {
        return "CraftVillager";
    }

    @Override
    public void remove() {
        this.getHandle().m_35524_();
        super.remove();
    }

    @Override
    public Villager.Profession getProfession() {
        return CraftVillager.nmsToBukkitProfession(this.getHandle().m_7141_().m_35571_());
    }

    @Override
    public void setProfession(Villager.Profession profession) {
        Preconditions.checkArgument((profession != null ? 1 : 0) != 0, (Object)"Profession cannot be null");
        this.getHandle().m_34375_(this.getHandle().m_7141_().m_35565_(CraftVillager.bukkitToNmsProfession(profession)));
    }

    @Override
    public Villager.Type getVillagerType() {
        return Villager.Type.valueOf(BuiltInRegistries.f_256934_.m_7981_((Object)this.getHandle().m_7141_().m_35560_()).m_135815_().toUpperCase(Locale.ROOT));
    }

    @Override
    public void setVillagerType(Villager.Type type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Type cannot be null");
        this.getHandle().m_34375_(this.getHandle().m_7141_().m_35567_((VillagerType)BuiltInRegistries.f_256934_.m_7745_(CraftNamespacedKey.toMinecraft(type.getKey()))));
    }

    @Override
    public int getVillagerLevel() {
        return this.getHandle().m_7141_().m_35576_();
    }

    @Override
    public void setVillagerLevel(int level) {
        Preconditions.checkArgument((1 <= level && level <= 5 ? 1 : 0) != 0, (String)"level (%s) must be between [1, 5]", (int)level);
        this.getHandle().m_34375_(this.getHandle().m_7141_().m_35561_(level));
    }

    @Override
    public int getVillagerExperience() {
        return this.getHandle().m_7809_();
    }

    @Override
    public void setVillagerExperience(int experience) {
        Preconditions.checkArgument((experience >= 0 ? 1 : 0) != 0, (String)"Experience (%s) must be positive", (int)experience);
        this.getHandle().m_35546_(experience);
    }

    @Override
    public boolean sleep(Location location) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((location.getWorld() != null ? 1 : 0) != 0, (Object)"Location needs to be in a world");
        Preconditions.checkArgument((boolean)location.getWorld().equals(this.getWorld()), (Object)"Cannot sleep across worlds");
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot sleep during world generation");
        BlockPos position = CraftLocation.toBlockPosition(location);
        BlockState iblockdata = this.getHandle().m_9236_().m_8055_(position);
        if (!(iblockdata.m_60734_() instanceof BedBlock)) {
            return false;
        }
        this.getHandle().m_5802_(position);
        return true;
    }

    @Override
    public void wakeup() {
        Preconditions.checkState((boolean)this.isSleeping(), (Object)"Cannot wakeup if not sleeping");
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot wakeup during world generation");
        this.getHandle().m_5796_();
    }

    @Override
    public void shakeHead() {
        this.getHandle().m_35518_();
    }

    @Override
    public ZombieVillager zombify() {
        net.minecraft.world.entity.monster.ZombieVillager entityzombievillager = Zombie.zombifyVillager((ServerLevel)this.getHandle().m_9236_().getMinecraftWorld(), (net.minecraft.world.entity.npc.Villager)this.getHandle(), (BlockPos)this.getHandle().m_20183_(), (boolean)this.isSilent(), (CreatureSpawnEvent.SpawnReason)CreatureSpawnEvent.SpawnReason.CUSTOM);
        return entityzombievillager != null ? (ZombieVillager)((Object)entityzombievillager.getBukkitEntity()) : null;
    }

    public static Villager.Profession nmsToBukkitProfession(VillagerProfession nms) {
        return Villager.Profession.valueOf(BuiltInRegistries.f_256735_.m_7981_((Object)nms).m_135815_().toUpperCase(Locale.ROOT));
    }

    public static VillagerProfession bukkitToNmsProfession(Villager.Profession bukkit) {
        return (VillagerProfession)BuiltInRegistries.f_256735_.m_7745_(CraftNamespacedKey.toMinecraft(bukkit.getKey()));
    }
}

