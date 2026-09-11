/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.util.profiling.ProfilerFiller
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeManager
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.crafting.CraftingHelper
 *  net.minecraftforge.common.crafting.conditions.ICondition$IContext
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.item.crafting.RecipeManagerBridge;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={RecipeManager.class})
public abstract class RecipeManagerMixin
implements RecipeManagerBridge {
    @Shadow
    public Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> f_44007_;
    @Shadow
    private boolean f_44008_;
    @Shadow
    @Final
    private static Logger f_44006_;
    @Shadow
    private Map<ResourceLocation, Recipe<?>> f_199900_;
    @Shadow(remap=false)
    @Final
    private ICondition.IContext context;

    @Shadow
    protected abstract <C extends Container, T extends Recipe<C>> Map<ResourceLocation, T> m_44054_(RecipeType<T> var1);

    @Shadow(remap=false)
    public static Recipe<?> fromJson(ResourceLocation recipeId, JsonObject json, ICondition.IContext context) {
        return null;
    }

    @Overwrite
    protected void m_5787_(Map<ResourceLocation, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        this.f_44008_ = false;
        HashMap map = Maps.newHashMap();
        for (RecipeType type : BuiltInRegistries.f_256990_) {
            map.put(type, new Object2ObjectLinkedOpenHashMap());
        }
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (Map.Entry<ResourceLocation, JsonElement> entry : objectIn.entrySet()) {
            ResourceLocation resourcelocation = entry.getKey();
            if (resourcelocation.m_135815_().startsWith("_")) continue;
            try {
                if (entry.getValue().isJsonObject() && !CraftingHelper.processConditions((JsonObject)entry.getValue().getAsJsonObject(), (String)"conditions", (ICondition.IContext)this.context)) {
                    f_44006_.debug("Skipping loading recipe {} as it's conditions were not met", (Object)resourcelocation);
                    continue;
                }
                Recipe<?> irecipe = RecipeManagerMixin.fromJson(resourcelocation, GsonHelper.m_13918_((JsonElement)entry.getValue(), (String)"top element"), this.context);
                if (irecipe == null) {
                    f_44006_.info("Skipping loading recipe {} as it's serializer returned null", (Object)resourcelocation);
                    continue;
                }
                map.computeIfAbsent(irecipe.m_6671_(), recipeType -> new Object2ObjectLinkedOpenHashMap()).putAndMoveToFirst((Object)resourcelocation, irecipe);
                builder.put((Object)resourcelocation, irecipe);
            }
            catch (JsonParseException | IllegalArgumentException jsonparseexception) {
                f_44006_.error("Parsing error loading recipe {}", (Object)resourcelocation, (Object)jsonparseexception);
            }
        }
        this.f_44007_ = map;
        this.f_199900_ = Maps.newHashMap((Map)builder.build());
        f_44006_.info("Loaded {} recipes", (Object)map.size());
    }

    @Overwrite
    public <C extends Container, T extends Recipe<C>> Optional<T> m_44015_(RecipeType<T> recipeTypeIn, C inventoryIn, Level worldIn) {
        Optional<Recipe> optional = this.m_44054_(recipeTypeIn).values().stream().filter(recipe -> recipe.m_5818_(inventoryIn, worldIn)).findFirst();
        ((IInventoryBridge)inventoryIn).setCurrentRecipe(optional.orElse(null));
        return optional;
    }

    public void addRecipe(Recipe<?> recipe) {
        Object2ObjectLinkedOpenHashMap map;
        Map<ResourceLocation, Recipe<?>> original;
        if (this.f_44007_ instanceof ImmutableMap) {
            this.f_44007_ = new HashMap(this.f_44007_);
        }
        if (this.f_199900_ instanceof ImmutableMap) {
            this.f_199900_ = new HashMap(this.f_199900_);
        }
        if (!((original = this.f_44007_.get(recipe.m_6671_())) instanceof Object2ObjectLinkedOpenHashMap)) {
            Object2ObjectLinkedOpenHashMap hashMap = new Object2ObjectLinkedOpenHashMap();
            hashMap.putAll(original);
            this.f_44007_.put((RecipeType<?>)recipe.m_6671_(), (Map<ResourceLocation, Recipe<?>>)hashMap);
            map = hashMap;
        } else {
            map = (Object2ObjectLinkedOpenHashMap)original;
        }
        if (this.f_199900_.containsKey(recipe.m_6423_()) || map.containsKey((Object)recipe.m_6423_())) {
            throw new IllegalStateException("Duplicate recipe ignored with ID " + String.valueOf(recipe.m_6423_()));
        }
        map.putAndMoveToFirst((Object)recipe.m_6423_(), recipe);
        this.f_199900_.put(recipe.m_6423_(), recipe);
    }

    @Override
    public void bridge$addRecipe(Recipe<?> recipe) {
        this.addRecipe(recipe);
    }

    public boolean removeRecipe(ResourceLocation mcKey) {
        for (Map<ResourceLocation, Recipe<?>> recipes : this.f_44007_.values()) {
            recipes.remove(mcKey);
        }
        return this.f_199900_.remove(mcKey) != null;
    }

    public void clearRecipes() {
        this.f_44007_ = new HashMap();
        for (RecipeType type : BuiltInRegistries.f_256990_) {
            this.f_44007_.put((RecipeType<?>)type, (Map<ResourceLocation, Recipe<?>>)new Object2ObjectLinkedOpenHashMap());
        }
        this.f_199900_ = new HashMap();
    }

    @Override
    public void bridge$clearRecipes() {
        this.clearRecipes();
    }
}

