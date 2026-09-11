/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.EnumHelper
 *  net.minecraft.world.entity.monster.SpellcasterIllager$IllagerSpell
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.api.EnumHelper;
import io.izzel.arclight.common.mod.ArclightMod;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSpellcaster;
import org.bukkit.entity.Spellcaster;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={CraftSpellcaster.class}, remap=false)
public class CraftSpellcasterMixin {
    @Overwrite
    public static Spellcaster.Spell toBukkitSpell(SpellcasterIllager.IllagerSpell spell) {
        try {
            return Spellcaster.Spell.valueOf(spell.name());
        }
        catch (IllegalArgumentException e) {
            ArrayList<Spellcaster.Spell> newTypes = new ArrayList<Spellcaster.Spell>();
            int forgeCount = SpellcasterIllager.IllagerSpell.values().length;
            for (int id = Spellcaster.Spell.values().length; id < forgeCount; ++id) {
                String name = SpellcasterIllager.IllagerSpell.values()[id].name();
                Spellcaster.Spell newPhase = (Spellcaster.Spell)((Object)EnumHelper.makeEnum(Spellcaster.Spell.class, (String)name, (int)id, List.of(), List.of()));
                newTypes.add(newPhase);
                ArclightMod.LOGGER.debug("Registered {} as illager spell {}", (Object)name, (Object)newPhase);
            }
            EnumHelper.addEnums(Spellcaster.Spell.class, newTypes);
            return CraftSpellcasterMixin.toBukkitSpell(spell);
        }
    }
}

