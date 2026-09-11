/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.BlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.command;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.command.ServerCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;

public class CraftBlockCommandSender
extends ServerCommandSender
implements BlockCommandSender {
    private final CommandSourceStack block;
    private final BlockEntity tile;

    public CraftBlockCommandSender(CommandSourceStack commandBlockListenerAbstract, BlockEntity tile) {
        this.block = commandBlockListenerAbstract;
        this.tile = tile;
    }

    @Override
    public Block getBlock() {
        return CraftBlock.at((LevelAccessor)this.tile.m_58904_(), this.tile.m_58899_());
    }

    @Override
    public void sendMessage(String message) {
        Component[] componentArray = CraftChatMessage.fromString(message);
        int n = componentArray.length;
        int n2 = 0;
        while (n2 < n) {
            Component component = componentArray[n2];
            this.block.f_81288_.m_213846_(component);
            ++n2;
        }
    }

    @Override
    public void sendMessage(String ... messages) {
        String[] stringArray = messages;
        int n = messages.length;
        int n2 = 0;
        while (n2 < n) {
            String message = stringArray[n2];
            this.sendMessage(message);
            ++n2;
        }
    }

    @Override
    public String getName() {
        return this.block.m_81368_();
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Cannot change operator status of a block");
    }

    public CommandSourceStack getWrapper() {
        return this.block;
    }
}

