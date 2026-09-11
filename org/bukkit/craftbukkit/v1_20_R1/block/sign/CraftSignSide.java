/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.level.block.entity.SignText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.craftbukkit.v1_20_R1.block.sign;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.SignText;
import org.bukkit.DyeColor;
import org.bukkit.block.sign.SignSide;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSign;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CraftSignSide
implements SignSide {
    private String[] originalLines = null;
    private String[] lines = null;
    private SignText signText;

    public CraftSignSide(SignText signText) {
        this.signText = signText;
    }

    @Override
    @NotNull
    public String[] getLines() {
        if (this.lines == null) {
            Component[] messages = this.signText.m_276945_(false);
            this.lines = new String[messages.length];
            System.arraycopy(CraftSign.revertComponents(messages), 0, this.lines, 0, this.lines.length);
            this.originalLines = new String[this.lines.length];
            System.arraycopy(this.lines, 0, this.originalLines, 0, this.originalLines.length);
        }
        return this.lines;
    }

    @Override
    @NotNull
    public String getLine(int index) throws IndexOutOfBoundsException {
        return this.getLines()[index];
    }

    @Override
    public void setLine(int index, @NotNull String line) throws IndexOutOfBoundsException {
        this.getLines()[index] = line;
    }

    @Override
    public boolean isGlowingText() {
        return this.signText.m_276843_();
    }

    @Override
    public void setGlowingText(boolean glowing) {
        this.signText = this.signText.m_277132_(glowing);
    }

    @Override
    @Nullable
    public DyeColor getColor() {
        return DyeColor.getByWoolData((byte)this.signText.m_276773_().m_41060_());
    }

    @Override
    public void setColor(@NotNull DyeColor color) {
        this.signText = this.signText.m_276901_(net.minecraft.world.item.DyeColor.m_41053_((int)color.getWoolData()));
    }

    public SignText applyLegacyStringToSignSide() {
        if (this.lines != null) {
            int i = 0;
            while (i < this.lines.length) {
                String line;
                String string = line = this.lines[i] == null ? "" : this.lines[i];
                if (!line.equals(this.originalLines[i])) {
                    this.signText = this.signText.m_276913_(i, CraftChatMessage.fromString(line)[0]);
                }
                ++i;
            }
        }
        return this.signText;
    }
}

