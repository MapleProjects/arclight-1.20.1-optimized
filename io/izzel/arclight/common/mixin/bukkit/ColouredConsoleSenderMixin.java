/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jline.Terminal
 *  jline.console.ConsoleReader
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.mixin.bukkit.CraftConsoleCommandSenderMixin;
import jline.Terminal;
import jline.console.ConsoleReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.craftbukkit.v1_20_R1.command.ColouredConsoleSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ColouredConsoleSender.class}, remap=false)
public class ColouredConsoleSenderMixin
extends CraftConsoleCommandSenderMixin {
    private static final Logger LOGGER = LogManager.getLogger((String)"Console");

    @Redirect(method={"<init>"}, at=@At(value="INVOKE", target="Ljline/console/ConsoleReader;getTerminal()Ljline/Terminal;"))
    private Terminal arclight$terminal(ConsoleReader instance) {
        return null;
    }

    @Overwrite
    public void sendMessage(String message) {
        if (!this.conversationTracker.isConversingModaly()) {
            LOGGER.info(message);
        }
    }
}

