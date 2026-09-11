/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Display
 *  net.minecraft.world.entity.Display$TextDisplay
 *  net.minecraft.world.entity.Display$TextDisplay$Align
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.Display;
import org.bukkit.Color;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftDisplay;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.entity.TextDisplay;

public class CraftTextDisplay
extends CraftDisplay
implements TextDisplay {
    public CraftTextDisplay(CraftServer server, Display.TextDisplay entity) {
        super(server, (Display)entity);
    }

    public Display.TextDisplay getHandle() {
        return (Display.TextDisplay)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftTextDisplay";
    }

    @Override
    public String getText() {
        return CraftChatMessage.fromComponent(this.getHandle().m_269000_());
    }

    @Override
    public void setText(String text) {
        this.getHandle().m_269037_(CraftChatMessage.fromString(text, true)[0]);
    }

    @Override
    public int getLineWidth() {
        return this.getHandle().m_269517_();
    }

    @Override
    public void setLineWidth(int width) {
        this.getHandle().m_20088_().m_135381_(Display.TextDisplay.f_268476_, (Object)width);
    }

    @Override
    public Color getBackgroundColor() {
        int color = this.getHandle().m_269375_();
        return color == -1 ? null : Color.fromARGB(color);
    }

    @Override
    public void setBackgroundColor(Color color) {
        if (color == null) {
            this.getHandle().m_20088_().m_135381_(Display.TextDisplay.f_268494_, (Object)-1);
        } else {
            this.getHandle().m_20088_().m_135381_(Display.TextDisplay.f_268494_, (Object)color.asARGB());
        }
    }

    @Override
    public byte getTextOpacity() {
        return this.getHandle().m_269180_();
    }

    @Override
    public void setTextOpacity(byte opacity) {
        this.getHandle().m_269007_(opacity);
    }

    @Override
    public boolean isShadowed() {
        return this.getFlag(1);
    }

    @Override
    public void setShadowed(boolean shadow) {
        this.setFlag(1, shadow);
    }

    @Override
    public boolean isSeeThrough() {
        return this.getFlag(2);
    }

    @Override
    public void setSeeThrough(boolean seeThrough) {
        this.setFlag(2, seeThrough);
    }

    @Override
    public boolean isDefaultBackground() {
        return this.getFlag(4);
    }

    @Override
    public void setDefaultBackground(boolean defaultBackground) {
        this.setFlag(4, defaultBackground);
    }

    @Override
    public TextDisplay.TextAlignment getAlignment() {
        Display.TextDisplay.Align nms = Display.TextDisplay.m_269384_((byte)this.getHandle().m_269327_());
        return TextDisplay.TextAlignment.valueOf(nms.name());
    }

    @Override
    public void setAlignment(TextDisplay.TextAlignment alignment) {
        Preconditions.checkArgument((alignment != null ? 1 : 0) != 0, (Object)"Alignment cannot be null");
        switch (alignment) {
            case LEFT: {
                this.setFlag(8, true);
                this.setFlag(16, false);
                break;
            }
            case RIGHT: {
                this.setFlag(8, false);
                this.setFlag(16, true);
                break;
            }
            case CENTER: {
                this.setFlag(8, false);
                this.setFlag(16, false);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown alignment " + (Object)((Object)alignment));
            }
        }
    }

    private boolean getFlag(int flag) {
        return (this.getHandle().m_269327_() & flag) != 0;
    }

    private void setFlag(int flag, boolean set) {
        byte flagBits = this.getHandle().m_269327_();
        flagBits = set ? (byte)(flagBits | flag) : (byte)(flagBits & ~flag);
        this.getHandle().m_269559_(flagBits);
    }
}

