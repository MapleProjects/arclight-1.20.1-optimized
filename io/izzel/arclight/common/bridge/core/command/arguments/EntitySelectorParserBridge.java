/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.arguments.selector.EntitySelector
 */
package io.izzel.arclight.common.bridge.core.command.arguments;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.arguments.selector.EntitySelector;

public interface EntitySelectorParserBridge {
    public EntitySelector bridge$parse(boolean var1) throws CommandSyntaxException;

    public void bridge$parseSelector(boolean var1) throws CommandSyntaxException;
}

