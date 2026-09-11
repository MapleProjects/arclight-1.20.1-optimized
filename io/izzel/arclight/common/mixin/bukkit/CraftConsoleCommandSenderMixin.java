/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.bukkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftConsoleCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.conversations.ConversationTracker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={CraftConsoleCommandSender.class}, remap=false)
public class CraftConsoleCommandSenderMixin {
    private static final Logger LOGGER = LogManager.getLogger((String)"Console");
    @Shadow
    @Final
    protected ConversationTracker conversationTracker;

    @Overwrite
    public void sendRawMessage(String message) {
        LOGGER.info(message);
    }
}

