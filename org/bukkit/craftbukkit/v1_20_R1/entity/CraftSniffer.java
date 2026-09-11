/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.sniffer.Sniffer
 *  net.minecraft.world.entity.animal.sniffer.Sniffer$State
 *  net.minecraft.world.level.Level
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.entity.Sniffer;

public class CraftSniffer
extends CraftAnimals
implements Sniffer {
    public CraftSniffer(CraftServer server, net.minecraft.world.entity.animal.sniffer.Sniffer entity) {
        super(server, (Animal)entity);
    }

    public net.minecraft.world.entity.animal.sniffer.Sniffer getHandle() {
        return (net.minecraft.world.entity.animal.sniffer.Sniffer)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftSniffer";
    }

    @Override
    public Collection<Location> getExploredLocations() {
        return this.getHandle().m_272217_().map(blockPosition -> CraftLocation.toBukkit(blockPosition.m_122646_(), (Level)this.server.getServer().m_129880_(blockPosition.m_122640_()))).collect(Collectors.toList());
    }

    @Override
    public void removeExploredLocation(Location location) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"location cannot be null");
        if (location.getWorld() != this.getWorld()) {
            return;
        }
        BlockPos blockPosition = CraftLocation.toBlockPosition(location);
        this.getHandle().m_6274_().m_21879_(MemoryModuleType.f_271415_, this.getHandle().m_272217_().filter(blockPositionExplored -> !blockPositionExplored.equals((Object)blockPosition)).collect(Collectors.toList()));
    }

    @Override
    public void addExploredLocation(Location location) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"location cannot be null");
        if (location.getWorld() != this.getWorld()) {
            return;
        }
        this.getHandle().m_271874_(CraftLocation.toBlockPosition(location));
    }

    @Override
    public Sniffer.State getState() {
        return this.stateToBukkit(this.getHandle().m_271917_());
    }

    @Override
    public void setState(Sniffer.State state) {
        Preconditions.checkArgument((state != null ? 1 : 0) != 0, (Object)"state cannot be null");
        this.getHandle().m_272034_(this.stateToNMS(state));
    }

    @Override
    public Location findPossibleDigLocation() {
        return this.getHandle().m_271905_().map(blockPosition -> CraftLocation.toBukkit(blockPosition, this.getLocation().getWorld())).orElse(null);
    }

    @Override
    public boolean canDig() {
        return this.getHandle().m_272270_();
    }

    private Sniffer.State stateToNMS(Sniffer.State state) {
        return switch (state) {
            case Sniffer.State.IDLING -> Sniffer.State.IDLING;
            case Sniffer.State.FEELING_HAPPY -> Sniffer.State.FEELING_HAPPY;
            case Sniffer.State.SCENTING -> Sniffer.State.SCENTING;
            case Sniffer.State.SNIFFING -> Sniffer.State.SNIFFING;
            case Sniffer.State.SEARCHING -> Sniffer.State.SEARCHING;
            case Sniffer.State.DIGGING -> Sniffer.State.DIGGING;
            case Sniffer.State.RISING -> Sniffer.State.RISING;
            default -> throw new IncompatibleClassChangeError();
        };
    }

    private Sniffer.State stateToBukkit(Sniffer.State state) {
        return switch (state) {
            case Sniffer.State.IDLING -> Sniffer.State.IDLING;
            case Sniffer.State.FEELING_HAPPY -> Sniffer.State.FEELING_HAPPY;
            case Sniffer.State.SCENTING -> Sniffer.State.SCENTING;
            case Sniffer.State.SNIFFING -> Sniffer.State.SNIFFING;
            case Sniffer.State.SEARCHING -> Sniffer.State.SEARCHING;
            case Sniffer.State.DIGGING -> Sniffer.State.DIGGING;
            case Sniffer.State.RISING -> Sniffer.State.RISING;
            default -> throw new IncompatibleClassChangeError();
        };
    }
}

