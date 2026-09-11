/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.commands.arguments.selector.EntitySelector
 *  net.minecraft.commands.arguments.selector.EntitySelectorParser
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.commands.arguments;

import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.izzel.arclight.common.bridge.core.command.arguments.EntityArgumentBridge;
import io.izzel.arclight.common.bridge.core.command.arguments.EntitySelectorParserBridge;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={EntityArgument.class})
public class EntityArgumentMixin
implements EntityArgumentBridge {
    @Shadow
    @Final
    boolean f_91443_;
    @Shadow
    @Final
    boolean f_91444_;

    @Override
    public EntitySelector bridge$parse(StringReader reader, boolean overridePermissions) throws CommandSyntaxException {
        return this.parse(reader, overridePermissions);
    }

    public EntitySelector parse(StringReader reader, boolean overridePermissions) throws CommandSyntaxException {
        boolean i = false;
        EntitySelectorParser entityselectorparser = new EntitySelectorParser(reader);
        EntitySelector entityselector = ((EntitySelectorParserBridge)entityselectorparser).bridge$parse(overridePermissions);
        if (entityselector.m_121138_() > 1 && this.f_91443_) {
            if (this.f_91444_) {
                reader.setCursor(0);
                throw EntityArgument.f_91437_.createWithContext((ImmutableStringReader)reader);
            }
            reader.setCursor(0);
            throw EntityArgument.f_91436_.createWithContext((ImmutableStringReader)reader);
        }
        if (entityselector.m_121159_() && this.f_91444_ && !entityselector.m_121162_()) {
            reader.setCursor(0);
            throw EntityArgument.f_91438_.createWithContext((ImmutableStringReader)reader);
        }
        return entityselector;
    }
}

