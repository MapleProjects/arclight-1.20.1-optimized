/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Interaction
 *  net.minecraft.world.entity.Interaction$PlayerAction
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import java.util.UUID;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Interaction;

public class CraftInteraction
extends CraftEntity
implements org.bukkit.entity.Interaction {
    public CraftInteraction(CraftServer server, Interaction entity) {
        super(server, (Entity)entity);
    }

    public Interaction getHandle() {
        return (Interaction)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftInteraction";
    }

    @Override
    public float getInteractionWidth() {
        return this.getHandle().m_272023_();
    }

    @Override
    public void setInteractionWidth(float width) {
        this.getHandle().m_272058_(width);
    }

    @Override
    public float getInteractionHeight() {
        return this.getHandle().m_271858_();
    }

    @Override
    public void setInteractionHeight(float height) {
        this.getHandle().m_271774_(height);
    }

    @Override
    public boolean isResponsive() {
        return this.getHandle().m_271819_();
    }

    @Override
    public void setResponsive(boolean response) {
        this.getHandle().m_271717_(response);
    }

    @Override
    public Interaction.PreviousInteraction getLastAttack() {
        Interaction.PlayerAction last = this.getHandle().f_271404_;
        return last != null ? new CraftPreviousInteraction(last.f_271379_(), last.f_271492_()) : null;
    }

    @Override
    public Interaction.PreviousInteraction getLastInteraction() {
        Interaction.PlayerAction last = this.getHandle().f_271193_;
        return last != null ? new CraftPreviousInteraction(last.f_271379_(), last.f_271492_()) : null;
    }

    private static class CraftPreviousInteraction
    implements Interaction.PreviousInteraction {
        private final UUID uuid;
        private final long timestamp;

        public CraftPreviousInteraction(UUID uuid, long timestamp) {
            this.uuid = uuid;
            this.timestamp = timestamp;
        }

        @Override
        public OfflinePlayer getPlayer() {
            return Bukkit.getOfflinePlayer(this.uuid);
        }

        @Override
        public long getTimestamp() {
            return this.timestamp;
        }
    }
}

