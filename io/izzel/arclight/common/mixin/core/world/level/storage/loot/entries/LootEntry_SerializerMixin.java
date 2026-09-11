/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
 *  net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer$Serializer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.storage.loot.entries;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={LootPoolEntryContainer.Serializer.class})
public abstract class LootEntry_SerializerMixin<T extends LootPoolEntryContainer> {
    @Shadow
    public abstract void m_6170_(JsonObject var1, T var2, JsonSerializationContext var3);

    @Shadow
    public abstract T m_7561_(JsonObject var1, JsonDeserializationContext var2);

    public final void a(JsonObject object, T t0, JsonSerializationContext context) {
        this.m_6170_(object, t0, context);
    }

    public final T a(JsonObject object, JsonDeserializationContext context) {
        return this.m_7561_(object, context);
    }
}

