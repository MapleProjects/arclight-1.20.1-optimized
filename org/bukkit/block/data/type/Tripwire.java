/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.Powerable;

public interface Tripwire
extends Attachable,
MultipleFacing,
Powerable {
    public boolean isDisarmed();

    public void setDisarmed(boolean var1);
}

