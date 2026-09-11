/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.generator.structure;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;

public abstract class StructureType
implements Keyed {
    public static final StructureType BURIED_TREASURE = StructureType.getStructureType("buried_treasure");
    public static final StructureType DESERT_PYRAMID = StructureType.getStructureType("desert_pyramid");
    public static final StructureType END_CITY = StructureType.getStructureType("end_city");
    public static final StructureType FORTRESS = StructureType.getStructureType("fortress");
    public static final StructureType IGLOO = StructureType.getStructureType("igloo");
    public static final StructureType JIGSAW = StructureType.getStructureType("jigsaw");
    public static final StructureType JUNGLE_TEMPLE = StructureType.getStructureType("jungle_temple");
    public static final StructureType MINESHAFT = StructureType.getStructureType("mineshaft");
    public static final StructureType NETHER_FOSSIL = StructureType.getStructureType("nether_fossil");
    public static final StructureType OCEAN_MONUMENT = StructureType.getStructureType("ocean_monument");
    public static final StructureType OCEAN_RUIN = StructureType.getStructureType("ocean_ruin");
    public static final StructureType RUINED_PORTAL = StructureType.getStructureType("ruined_portal");
    public static final StructureType SHIPWRECK = StructureType.getStructureType("shipwreck");
    public static final StructureType STRONGHOLD = StructureType.getStructureType("stronghold");
    public static final StructureType SWAMP_HUT = StructureType.getStructureType("swamp_hut");
    public static final StructureType WOODLAND_MANSION = StructureType.getStructureType("woodland_mansion");

    private static StructureType getStructureType(String name) {
        return Registry.STRUCTURE_TYPE.get(NamespacedKey.minecraft(name));
    }
}

