/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.world.entity.decoration.HangingEntity
 *  net.minecraft.world.entity.decoration.Painting
 *  net.minecraft.world.entity.decoration.PaintingVariant
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.bukkit.Art;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.CraftArt;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHanging;
import org.bukkit.entity.Painting;

public class CraftPainting
extends CraftHanging
implements Painting {
    public CraftPainting(CraftServer server, net.minecraft.world.entity.decoration.Painting entity) {
        super(server, (HangingEntity)entity);
    }

    @Override
    public Art getArt() {
        Holder art = this.getHandle().m_28554_();
        return CraftArt.NotchToBukkit((Holder<PaintingVariant>)art);
    }

    @Override
    public boolean setArt(Art art) {
        return this.setArt(art, false);
    }

    @Override
    public boolean setArt(Art art, boolean force) {
        net.minecraft.world.entity.decoration.Painting painting = this.getHandle();
        Holder oldArt = painting.m_28554_();
        painting.m_28464_(CraftArt.BukkitToNotch(art));
        painting.m_6022_(painting.m_6350_());
        if (!(force || this.getHandle().generation || painting.m_7088_())) {
            painting.m_28464_(oldArt);
            painting.m_6022_(painting.m_6350_());
            return false;
        }
        this.update();
        return true;
    }

    @Override
    public boolean setFacingDirection(BlockFace face, boolean force) {
        if (super.setFacingDirection(face, force)) {
            this.update();
            return true;
        }
        return false;
    }

    public net.minecraft.world.entity.decoration.Painting getHandle() {
        return (net.minecraft.world.entity.decoration.Painting)this.entity;
    }

    @Override
    public String toString() {
        return "CraftPainting{art=" + this.getArt() + "}";
    }
}

