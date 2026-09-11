/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.game.ClientboundRecipePacket$State
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.stats.ServerRecipeBook
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.crafting.Recipe
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import java.util.Collection;
import java.util.List;
import net.minecraft.network.protocol.game.ClientboundRecipePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerRecipeBook;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ServerRecipeBook.class})
public class ServerRecipeBookMixin {
    @Redirect(method={"addRecipes"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/item/crafting/Recipe;isSpecial()Z"))
    public boolean arclight$recipeUpdate(Recipe<?> recipe, Collection<Recipe<?>> collection, ServerPlayer playerEntity) {
        return recipe.m_5598_() || !CraftEventFactory.handlePlayerRecipeListUpdateEvent((Player)playerEntity, recipe.m_6423_());
    }

    @Inject(method={"sendRecipes"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$returnIfFail(ClientboundRecipePacket.State state, ServerPlayer player, List<ResourceLocation> recipesIn, CallbackInfo ci) {
        if (player.f_8906_ == null) {
            ci.cancel();
        }
    }
}

