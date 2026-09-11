/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SoundType
 */
package org.bukkit.craftbukkit.v1_20_R1;

import java.util.HashMap;
import net.minecraft.world.level.block.SoundType;
import org.bukkit.Sound;
import org.bukkit.SoundGroup;
import org.bukkit.craftbukkit.v1_20_R1.CraftSound;

public class CraftSoundGroup
implements SoundGroup {
    private final SoundType handle;
    private static final HashMap<SoundType, CraftSoundGroup> SOUND_GROUPS = new HashMap();

    public static SoundGroup getSoundGroup(SoundType soundEffectType) {
        return SOUND_GROUPS.computeIfAbsent(soundEffectType, CraftSoundGroup::new);
    }

    private CraftSoundGroup(SoundType soundEffectType) {
        this.handle = soundEffectType;
    }

    public SoundType getHandle() {
        return this.handle;
    }

    @Override
    public float getVolume() {
        return this.getHandle().m_56773_();
    }

    @Override
    public float getPitch() {
        return this.getHandle().m_56774_();
    }

    @Override
    public Sound getBreakSound() {
        return CraftSound.getBukkit(this.getHandle().f_56733_);
    }

    @Override
    public Sound getStepSound() {
        return CraftSound.getBukkit(this.getHandle().m_56776_());
    }

    @Override
    public Sound getPlaceSound() {
        return CraftSound.getBukkit(this.getHandle().m_56777_());
    }

    @Override
    public Sound getHitSound() {
        return CraftSound.getBukkit(this.getHandle().f_56737_);
    }

    @Override
    public Sound getFallSound() {
        return CraftSound.getBukkit(this.getHandle().m_56779_());
    }
}

