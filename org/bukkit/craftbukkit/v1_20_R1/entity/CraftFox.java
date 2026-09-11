/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Fox
 *  net.minecraft.world.entity.animal.Fox$Type
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Fox;

public class CraftFox
extends CraftAnimals
implements org.bukkit.entity.Fox {
    public CraftFox(CraftServer server, Fox entity) {
        super(server, (Animal)entity);
    }

    public Fox getHandle() {
        return (Fox)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftFox";
    }

    @Override
    public Fox.Type getFoxType() {
        return Fox.Type.values()[this.getHandle().m_28554_().ordinal()];
    }

    @Override
    public void setFoxType(Fox.Type type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"type");
        this.getHandle().m_28464_(Fox.Type.values()[type.ordinal()]);
    }

    @Override
    public boolean isCrouching() {
        return this.getHandle().m_6047_();
    }

    @Override
    public void setCrouching(boolean crouching) {
        this.getHandle().m_28614_(crouching);
    }

    @Override
    public boolean isSitting() {
        return this.getHandle().m_28555_();
    }

    @Override
    public void setSitting(boolean sitting) {
        this.getHandle().m_28610_(sitting);
    }

    @Override
    public void setSleeping(boolean sleeping) {
        this.getHandle().m_28626_(sleeping);
    }

    @Override
    public AnimalTamer getFirstTrustedPlayer() {
        UUID uuid = ((Optional)this.getHandle().m_20088_().m_135370_(Fox.f_28439_)).orElse(null);
        if (uuid == null) {
            return null;
        }
        OfflinePlayer player = this.getServer().getPlayer(uuid);
        if (player == null) {
            player = this.getServer().getOfflinePlayer(uuid);
        }
        return player;
    }

    @Override
    public void setFirstTrustedPlayer(AnimalTamer player) {
        if (player == null) {
            Preconditions.checkState((boolean)((Optional)this.getHandle().m_20088_().m_135370_(Fox.f_28440_)).isEmpty(), (Object)"Must remove second trusted player first");
        }
        this.getHandle().m_20088_().m_135381_(Fox.f_28439_, player == null ? Optional.empty() : Optional.of(player.getUniqueId()));
    }

    @Override
    public AnimalTamer getSecondTrustedPlayer() {
        UUID uuid = ((Optional)this.getHandle().m_20088_().m_135370_(Fox.f_28440_)).orElse(null);
        if (uuid == null) {
            return null;
        }
        OfflinePlayer player = this.getServer().getPlayer(uuid);
        if (player == null) {
            player = this.getServer().getOfflinePlayer(uuid);
        }
        return player;
    }

    @Override
    public void setSecondTrustedPlayer(AnimalTamer player) {
        if (player != null) {
            Preconditions.checkState((boolean)((Optional)this.getHandle().m_20088_().m_135370_(Fox.f_28439_)).isPresent(), (Object)"Must add first trusted player first");
        }
        this.getHandle().m_20088_().m_135381_(Fox.f_28440_, player == null ? Optional.empty() : Optional.of(player.getUniqueId()));
    }

    @Override
    public boolean isFaceplanted() {
        return this.getHandle().m_28556_();
    }
}

