/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.EndGateway;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;

public class CraftEndGateway
extends CraftBlockEntityState<TheEndGatewayBlockEntity>
implements EndGateway {
    public CraftEndGateway(World world, TheEndGatewayBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Location getExitLocation() {
        BlockPos pos = ((TheEndGatewayBlockEntity)this.getSnapshot()).f_59928_;
        return pos == null ? null : CraftLocation.toBukkit(pos, this.isPlaced() ? this.getWorld() : null);
    }

    @Override
    public void setExitLocation(Location location) {
        if (location == null) {
            ((TheEndGatewayBlockEntity)this.getSnapshot()).f_59928_ = null;
        } else {
            if (!Objects.equals(location.getWorld(), this.isPlaced() ? this.getWorld() : null)) {
                throw new IllegalArgumentException("Cannot set exit location to different world");
            }
            ((TheEndGatewayBlockEntity)this.getSnapshot()).f_59928_ = CraftLocation.toBlockPosition(location);
        }
    }

    @Override
    public boolean isExactTeleport() {
        return ((TheEndGatewayBlockEntity)this.getSnapshot()).f_59929_;
    }

    @Override
    public void setExactTeleport(boolean exact) {
        ((TheEndGatewayBlockEntity)this.getSnapshot()).f_59929_ = exact;
    }

    @Override
    public long getAge() {
        return ((TheEndGatewayBlockEntity)this.getSnapshot()).f_59926_;
    }

    @Override
    public void setAge(long age) {
        ((TheEndGatewayBlockEntity)this.getSnapshot()).f_59926_ = age;
    }

    @Override
    public void applyTo(TheEndGatewayBlockEntity endGateway) {
        super.applyTo(endGateway);
        if (((TheEndGatewayBlockEntity)this.getSnapshot()).f_59928_ == null) {
            endGateway.f_59928_ = null;
        }
    }
}

