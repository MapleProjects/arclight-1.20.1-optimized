/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.LockCode
 *  net.minecraft.world.level.block.entity.BaseContainerBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.LockCode;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import org.bukkit.World;
import org.bukkit.block.Container;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;

public abstract class CraftContainer<T extends BaseContainerBlockEntity>
extends CraftBlockEntityState<T>
implements Container {
    public CraftContainer(World world, T tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public boolean isLocked() {
        return !((BaseContainerBlockEntity)this.getSnapshot()).f_58621_.f_19103_.isEmpty();
    }

    @Override
    public String getLock() {
        return ((BaseContainerBlockEntity)this.getSnapshot()).f_58621_.f_19103_;
    }

    @Override
    public void setLock(String key) {
        ((BaseContainerBlockEntity)this.getSnapshot()).f_58621_ = key == null ? LockCode.f_19102_ : new LockCode(key);
    }

    @Override
    public String getCustomName() {
        BaseContainerBlockEntity container = (BaseContainerBlockEntity)this.getSnapshot();
        return container.f_58622_ != null ? CraftChatMessage.fromComponent(container.m_7770_()) : null;
    }

    @Override
    public void setCustomName(String name) {
        ((BaseContainerBlockEntity)this.getSnapshot()).m_58638_(CraftChatMessage.fromStringOrNull(name));
    }

    @Override
    public void applyTo(T container) {
        super.applyTo(container);
        if (((BaseContainerBlockEntity)this.getSnapshot()).f_58622_ == null) {
            container.m_58638_(null);
        }
    }
}

