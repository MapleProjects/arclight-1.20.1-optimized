/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.entity.vehicle.Boat$Status
 *  net.minecraft.world.entity.vehicle.Boat$Type
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.stream.Collectors;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import org.bukkit.TreeSpecies;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVehicle;
import org.bukkit.entity.Boat;

public class CraftBoat
extends CraftVehicle
implements org.bukkit.entity.Boat {
    public CraftBoat(CraftServer server, Boat entity) {
        super(server, (Entity)entity);
    }

    @Override
    public TreeSpecies getWoodType() {
        return CraftBoat.getTreeSpecies(this.getHandle().m_28554_());
    }

    @Override
    public void setWoodType(TreeSpecies species) {
        this.getHandle().m_28464_(CraftBoat.getBoatType(species));
    }

    @Override
    public Boat.Type getBoatType() {
        return CraftBoat.boatTypeFromNms(this.getHandle().m_28554_());
    }

    @Override
    public void setBoatType(Boat.Type type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Boat.Type cannot be null");
        this.getHandle().m_28464_(CraftBoat.boatTypeToNms(type));
    }

    @Override
    public double getMaxSpeed() {
        return this.getHandle().maxSpeed;
    }

    @Override
    public void setMaxSpeed(double speed) {
        if (speed >= 0.0) {
            this.getHandle().maxSpeed = speed;
        }
    }

    @Override
    public double getOccupiedDeceleration() {
        return this.getHandle().occupiedDeceleration;
    }

    @Override
    public void setOccupiedDeceleration(double speed) {
        if (speed >= 0.0) {
            this.getHandle().occupiedDeceleration = speed;
        }
    }

    @Override
    public double getUnoccupiedDeceleration() {
        return this.getHandle().unoccupiedDeceleration;
    }

    @Override
    public void setUnoccupiedDeceleration(double speed) {
        this.getHandle().unoccupiedDeceleration = speed;
    }

    @Override
    public boolean getWorkOnLand() {
        return this.getHandle().landBoats;
    }

    @Override
    public void setWorkOnLand(boolean workOnLand) {
        this.getHandle().landBoats = workOnLand;
    }

    @Override
    public Boat.Status getStatus() {
        return CraftBoat.boatStatusFromNms(this.getHandle().f_38279_);
    }

    public Boat getHandle() {
        return (Boat)this.entity;
    }

    @Override
    public String toString() {
        return "CraftBoat{boatType=" + (Object)((Object)this.getBoatType()) + ",status=" + (Object)((Object)this.getStatus()) + ",passengers=" + this.getPassengers().stream().map(Object::toString).collect(Collectors.joining("-", "{", "}")) + "}";
    }

    public static Boat.Type boatTypeFromNms(Boat.Type boatType) {
        return switch (boatType) {
            default -> throw new EnumConstantNotPresentException(Boat.Type.class, boatType.name());
            case Boat.Type.OAK -> Boat.Type.OAK;
            case Boat.Type.BIRCH -> Boat.Type.BIRCH;
            case Boat.Type.ACACIA -> Boat.Type.ACACIA;
            case Boat.Type.CHERRY -> Boat.Type.CHERRY;
            case Boat.Type.JUNGLE -> Boat.Type.JUNGLE;
            case Boat.Type.SPRUCE -> Boat.Type.SPRUCE;
            case Boat.Type.DARK_OAK -> Boat.Type.DARK_OAK;
            case Boat.Type.MANGROVE -> Boat.Type.MANGROVE;
            case Boat.Type.BAMBOO -> Boat.Type.BAMBOO;
        };
    }

    public static Boat.Type boatTypeToNms(Boat.Type type) {
        return switch (type) {
            default -> throw new EnumConstantNotPresentException(Boat.Type.class, type.name());
            case Boat.Type.BAMBOO -> Boat.Type.BAMBOO;
            case Boat.Type.MANGROVE -> Boat.Type.MANGROVE;
            case Boat.Type.SPRUCE -> Boat.Type.SPRUCE;
            case Boat.Type.DARK_OAK -> Boat.Type.DARK_OAK;
            case Boat.Type.JUNGLE -> Boat.Type.JUNGLE;
            case Boat.Type.CHERRY -> Boat.Type.CHERRY;
            case Boat.Type.ACACIA -> Boat.Type.ACACIA;
            case Boat.Type.BIRCH -> Boat.Type.BIRCH;
            case Boat.Type.OAK -> Boat.Type.OAK;
        };
    }

    public static Boat.Status boatStatusFromNms(Boat.Status enumStatus) {
        return switch (enumStatus) {
            default -> throw new EnumConstantNotPresentException(Boat.Status.class, enumStatus.name());
            case Boat.Status.IN_AIR -> Boat.Status.IN_AIR;
            case Boat.Status.ON_LAND -> Boat.Status.ON_LAND;
            case Boat.Status.UNDER_WATER -> Boat.Status.UNDER_WATER;
            case Boat.Status.UNDER_FLOWING_WATER -> Boat.Status.UNDER_FLOWING_WATER;
            case Boat.Status.IN_WATER -> Boat.Status.IN_WATER;
        };
    }

    @Deprecated
    public static TreeSpecies getTreeSpecies(Boat.Type boatType) {
        switch (boatType) {
            case SPRUCE: {
                return TreeSpecies.REDWOOD;
            }
            case BIRCH: {
                return TreeSpecies.BIRCH;
            }
            case JUNGLE: {
                return TreeSpecies.JUNGLE;
            }
            case ACACIA: {
                return TreeSpecies.ACACIA;
            }
            case DARK_OAK: {
                return TreeSpecies.DARK_OAK;
            }
        }
        return TreeSpecies.GENERIC;
    }

    @Deprecated
    public static Boat.Type getBoatType(TreeSpecies species) {
        switch (species) {
            case REDWOOD: {
                return Boat.Type.SPRUCE;
            }
            case BIRCH: {
                return Boat.Type.BIRCH;
            }
            case JUNGLE: {
                return Boat.Type.JUNGLE;
            }
            case ACACIA: {
                return Boat.Type.ACACIA;
            }
            case DARK_OAK: {
                return Boat.Type.DARK_OAK;
            }
        }
        return Boat.Type.OAK;
    }
}

