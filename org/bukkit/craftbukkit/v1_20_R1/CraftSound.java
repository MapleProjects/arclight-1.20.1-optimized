/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;

public class CraftSound {
    public static SoundEvent getSoundEffect(String s) {
        SoundEvent effect = (SoundEvent)BuiltInRegistries.f_256894_.m_7745_(new ResourceLocation(s));
        Preconditions.checkArgument((effect != null ? 1 : 0) != 0, (String)"Sound effect %s does not exist", (Object)s);
        return effect;
    }

    public static SoundEvent getSoundEffect(Sound s) {
        SoundEvent effect = (SoundEvent)BuiltInRegistries.f_256894_.m_7745_(CraftNamespacedKey.toMinecraft(s.getKey()));
        Preconditions.checkArgument((effect != null ? 1 : 0) != 0, (String)"Sound effect %s does not exist", (Object)s);
        return effect;
    }

    public static Sound getBukkit(SoundEvent soundEffect) {
        return Registry.SOUNDS.get(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.f_256894_.m_7981_((Object)soundEffect)));
    }
}

