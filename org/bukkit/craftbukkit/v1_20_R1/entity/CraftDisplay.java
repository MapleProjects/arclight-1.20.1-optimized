/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.mojang.math.Transformation
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.util.Brightness
 *  net.minecraft.world.entity.Display
 *  net.minecraft.world.entity.Display$BillboardConstraints
 *  net.minecraft.world.entity.Entity
 *  org.joml.Matrix4f
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Brightness;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import org.bukkit.Color;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Display;
import org.bukkit.util.Transformation;
import org.joml.Matrix4f;

public class CraftDisplay
extends CraftEntity
implements Display {
    public CraftDisplay(CraftServer server, net.minecraft.world.entity.Display entity) {
        super(server, (Entity)entity);
    }

    public net.minecraft.world.entity.Display getHandle() {
        return (net.minecraft.world.entity.Display)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftDisplay";
    }

    @Override
    public Transformation getTransformation() {
        com.mojang.math.Transformation nms = net.minecraft.world.entity.Display.m_269448_((SynchedEntityData)this.getHandle().m_20088_());
        return new Transformation(nms.m_252829_(), nms.m_253244_(), nms.m_252900_(), nms.m_252848_());
    }

    @Override
    public void setTransformation(Transformation transformation) {
        Preconditions.checkArgument((transformation != null ? 1 : 0) != 0, (Object)"Transformation cannot be null");
        this.getHandle().m_269214_(new com.mojang.math.Transformation(transformation.getTranslation(), transformation.getLeftRotation(), transformation.getScale(), transformation.getRightRotation()));
    }

    @Override
    public void setTransformationMatrix(Matrix4f transformationMatrix) {
        Preconditions.checkArgument((transformationMatrix != null ? 1 : 0) != 0, (Object)"Transformation matrix cannot be null");
        this.getHandle().m_269214_(new com.mojang.math.Transformation(transformationMatrix));
    }

    @Override
    public int getInterpolationDuration() {
        return this.getHandle().m_269272_();
    }

    @Override
    public void setInterpolationDuration(int duration) {
        this.getHandle().m_269317_(duration);
    }

    @Override
    public float getViewRange() {
        return this.getHandle().m_269081_();
    }

    @Override
    public void setViewRange(float range) {
        this.getHandle().m_269215_(range);
    }

    @Override
    public float getShadowRadius() {
        return this.getHandle().m_269459_();
    }

    @Override
    public void setShadowRadius(float radius) {
        this.getHandle().m_269526_(radius);
    }

    @Override
    public float getShadowStrength() {
        return this.getHandle().m_269155_();
    }

    @Override
    public void setShadowStrength(float strength) {
        this.getHandle().m_269228_(strength);
    }

    @Override
    public float getDisplayWidth() {
        return this.getHandle().m_269558_();
    }

    @Override
    public void setDisplayWidth(float width) {
        this.getHandle().m_269441_(width);
    }

    @Override
    public float getDisplayHeight() {
        return this.getHandle().m_269410_();
    }

    @Override
    public void setDisplayHeight(float height) {
        this.getHandle().m_269087_(height);
    }

    @Override
    public int getInterpolationDelay() {
        return this.getHandle().m_276347_();
    }

    @Override
    public void setInterpolationDelay(int ticks) {
        this.getHandle().m_276345_(ticks);
    }

    @Override
    public Display.Billboard getBillboard() {
        return Display.Billboard.valueOf(this.getHandle().m_269218_().name());
    }

    @Override
    public void setBillboard(Display.Billboard billboard) {
        Preconditions.checkArgument((billboard != null ? 1 : 0) != 0, (Object)"Billboard cannot be null");
        this.getHandle().m_269423_(Display.BillboardConstraints.valueOf((String)billboard.name()));
    }

    @Override
    public Color getGlowColorOverride() {
        int color = this.getHandle().m_269034_();
        return color == -1 ? null : Color.fromARGB(color);
    }

    @Override
    public void setGlowColorOverride(Color color) {
        if (color == null) {
            this.getHandle().m_269026_(-1);
        } else {
            this.getHandle().m_269026_(color.asARGB());
        }
    }

    @Override
    public Display.Brightness getBrightness() {
        Brightness nms = this.getHandle().m_269102_();
        return nms != null ? new Display.Brightness(nms.f_268416_(), nms.f_268420_()) : null;
    }

    @Override
    public void setBrightness(Display.Brightness brightness) {
        if (brightness != null) {
            this.getHandle().m_269586_(new Brightness(brightness.getBlockLight(), brightness.getSkyLight()));
        } else {
            this.getHandle().m_269586_(null);
        }
    }
}

