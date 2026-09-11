/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Sittable;

public interface Camel
extends AbstractHorse,
Sittable {
    public boolean isDashing();

    public void setDashing(boolean var1);
}

