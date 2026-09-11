/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.arguments.selector.EntitySelector
 *  net.minecraft.commands.arguments.selector.EntitySelectorParser
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.commands.arguments.selector;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.izzel.arclight.common.bridge.core.command.arguments.EntitySelectorParserBridge;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntitySelectorParser.class})
public abstract class EntitySelectorParserMixin
implements EntitySelectorParserBridge {
    @Shadow
    private boolean f_121189_;
    private Boolean arclight$overridePermissions;

    @Shadow
    protected abstract void shadow$m_121281_() throws CommandSyntaxException;

    @Shadow
    public abstract EntitySelector m_121377_() throws CommandSyntaxException;

    @Override
    public EntitySelector bridge$parse(boolean overridePermissions) throws CommandSyntaxException {
        return this.parse(overridePermissions);
    }

    public EntitySelector parse(boolean overridePermissions) throws CommandSyntaxException {
        try {
            this.arclight$overridePermissions = overridePermissions;
            EntitySelector entitySelector = this.m_121377_();
            return entitySelector;
        }
        finally {
            this.arclight$overridePermissions = null;
        }
    }

    @Override
    public void bridge$parseSelector(boolean overridePermissions) throws CommandSyntaxException {
        this.parseSelector(overridePermissions);
    }

    public void parseSelector(boolean overridePermissions) throws CommandSyntaxException {
        this.f_121189_ = !overridePermissions;
        this.shadow$m_121281_();
    }

    @Inject(method={"parseSelector"}, at={@At(value="HEAD")})
    public void arclight$onParserSelector(CallbackInfo ci) {
        if (this.arclight$overridePermissions != null) {
            this.f_121189_ = this.arclight$overridePermissions == false;
        }
    }
}

