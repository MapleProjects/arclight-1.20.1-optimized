/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 */
package io.izzel.arclight.common.mod.compat;

import net.minecraft.world.entity.Entity;

public class AstralSorceryHooks {
    private static Class<?> interactClass;

    public static boolean notInteractable(Entity entity) {
        return interactClass == null || !interactClass.isInstance(entity);
    }

    static {
        try {
            interactClass = Class.forName("hellfirepvp.astralsorcery.common.entity.InteractableEntity");
        }
        catch (ClassNotFoundException e) {
            interactClass = null;
        }
    }
}

