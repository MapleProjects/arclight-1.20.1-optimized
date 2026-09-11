/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.generator.structure;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.generator.structure.StructureType;
import org.jetbrains.annotations.NotNull;

public abstract class Structure
implements Keyed {
    public static final Structure PILLAGER_OUTPOST = Structure.getStructure("pillager_outpost");
    public static final Structure MINESHAFT = Structure.getStructure("mineshaft");
    public static final Structure MINESHAFT_MESA = Structure.getStructure("mineshaft_mesa");
    public static final Structure MANSION = Structure.getStructure("mansion");
    public static final Structure JUNGLE_PYRAMID = Structure.getStructure("jungle_pyramid");
    public static final Structure DESERT_PYRAMID = Structure.getStructure("desert_pyramid");
    public static final Structure IGLOO = Structure.getStructure("igloo");
    public static final Structure SHIPWRECK = Structure.getStructure("shipwreck");
    public static final Structure SHIPWRECK_BEACHED = Structure.getStructure("shipwreck_beached");
    public static final Structure SWAMP_HUT = Structure.getStructure("swamp_hut");
    public static final Structure STRONGHOLD = Structure.getStructure("stronghold");
    public static final Structure MONUMENT = Structure.getStructure("monument");
    public static final Structure OCEAN_RUIN_COLD = Structure.getStructure("ocean_ruin_cold");
    public static final Structure OCEAN_RUIN_WARM = Structure.getStructure("ocean_ruin_warm");
    public static final Structure FORTRESS = Structure.getStructure("fortress");
    public static final Structure NETHER_FOSSIL = Structure.getStructure("nether_fossil");
    public static final Structure END_CITY = Structure.getStructure("end_city");
    public static final Structure BURIED_TREASURE = Structure.getStructure("buried_treasure");
    public static final Structure BASTION_REMNANT = Structure.getStructure("bastion_remnant");
    public static final Structure VILLAGE_PLAINS = Structure.getStructure("village_plains");
    public static final Structure VILLAGE_DESERT = Structure.getStructure("village_desert");
    public static final Structure VILLAGE_SAVANNA = Structure.getStructure("village_savanna");
    public static final Structure VILLAGE_SNOWY = Structure.getStructure("village_snowy");
    public static final Structure VILLAGE_TAIGA = Structure.getStructure("village_taiga");
    public static final Structure RUINED_PORTAL = Structure.getStructure("ruined_portal");
    public static final Structure RUINED_PORTAL_DESERT = Structure.getStructure("ruined_portal_desert");
    public static final Structure RUINED_PORTAL_JUNGLE = Structure.getStructure("ruined_portal_jungle");
    public static final Structure RUINED_PORTAL_SWAMP = Structure.getStructure("ruined_portal_swamp");
    public static final Structure RUINED_PORTAL_MOUNTAIN = Structure.getStructure("ruined_portal_mountain");
    public static final Structure RUINED_PORTAL_OCEAN = Structure.getStructure("ruined_portal_ocean");
    public static final Structure RUINED_PORTAL_NETHER = Structure.getStructure("ruined_portal_nether");
    public static final Structure ANCIENT_CITY = Structure.getStructure("ancient_city");
    public static final Structure TRAIL_RUINS = Structure.getStructure("trail_ruins");

    private static Structure getStructure(String name) {
        return Registry.STRUCTURE.get(NamespacedKey.minecraft(name));
    }

    @NotNull
    public abstract StructureType getStructureType();
}

