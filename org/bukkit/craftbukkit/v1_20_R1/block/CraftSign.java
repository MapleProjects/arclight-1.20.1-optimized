/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.bukkit.DyeColor;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.block.sign.CraftSignSide;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSignOpenEvent;
import org.jetbrains.annotations.NotNull;

public class CraftSign<T extends SignBlockEntity>
extends CraftBlockEntityState<T>
implements Sign {
    private final CraftSignSide front = new CraftSignSide(((SignBlockEntity)this.getSnapshot()).m_277142_());
    private final CraftSignSide back = new CraftSignSide(((SignBlockEntity)this.getSnapshot()).m_277159_());

    public CraftSign(World world, T tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public String[] getLines() {
        return this.front.getLines();
    }

    @Override
    public String getLine(int index) throws IndexOutOfBoundsException {
        return this.front.getLine(index);
    }

    @Override
    public void setLine(int index, String line) throws IndexOutOfBoundsException {
        this.front.setLine(index, line);
    }

    @Override
    public boolean isEditable() {
        return !this.isWaxed();
    }

    @Override
    public void setEditable(boolean editable) {
        this.setWaxed(!editable);
    }

    @Override
    public boolean isWaxed() {
        return ((SignBlockEntity)this.getSnapshot()).m_277118_();
    }

    @Override
    public void setWaxed(boolean waxed) {
        ((SignBlockEntity)this.getSnapshot()).m_277031_(waxed);
    }

    @Override
    public boolean isGlowingText() {
        return this.front.isGlowingText();
    }

    @Override
    public void setGlowingText(boolean glowing) {
        this.front.setGlowingText(glowing);
    }

    @Override
    @NotNull
    public SignSide getSide(Side side) {
        Preconditions.checkArgument((side != null ? 1 : 0) != 0, (Object)"side == null");
        switch (side) {
            case FRONT: {
                return this.front;
            }
            case BACK: {
                return this.back;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public DyeColor getColor() {
        return this.front.getColor();
    }

    @Override
    public void setColor(DyeColor color) {
        this.front.setColor(color);
    }

    @Override
    public void applyTo(T sign) {
        ((SignBlockEntity)this.getSnapshot()).m_276956_(this.front.applyLegacyStringToSignSide(), true);
        ((SignBlockEntity)this.getSnapshot()).m_276956_(this.back.applyLegacyStringToSignSide(), false);
        super.applyTo(sign);
    }

    public static void openSign(Sign sign, Player player, Side side) {
        Preconditions.checkArgument((sign != null ? 1 : 0) != 0, (Object)"sign == null");
        Preconditions.checkArgument((side != null ? 1 : 0) != 0, (Object)"side == null");
        Preconditions.checkArgument((boolean)sign.isPlaced(), (Object)"Sign must be placed");
        Preconditions.checkArgument((sign.getWorld() == player.getWorld() ? 1 : 0) != 0, (Object)"Sign must be in same world as Player");
        if (!CraftEventFactory.callPlayerSignOpenEvent(player, sign, side, PlayerSignOpenEvent.Cause.PLUGIN)) {
            return;
        }
        SignBlockEntity handle = (SignBlockEntity)((CraftSign)sign).getTileEntity();
        handle.m_155713_(player.getUniqueId());
        ((CraftPlayer)player).getHandle().m_7739_(handle, Side.FRONT == side);
    }

    public static Component[] sanitizeLines(String[] lines) {
        Component[] components = new Component[4];
        int i = 0;
        while (i < 4) {
            components[i] = i < lines.length && lines[i] != null ? CraftChatMessage.fromString(lines[i])[0] : Component.m_237119_();
            ++i;
        }
        return components;
    }

    public static String[] revertComponents(Component[] components) {
        String[] lines = new String[components.length];
        int i = 0;
        while (i < lines.length) {
            lines[i] = CraftSign.revertComponent(components[i]);
            ++i;
        }
        return lines;
    }

    private static String revertComponent(Component component) {
        return CraftChatMessage.fromComponent(component);
    }
}

