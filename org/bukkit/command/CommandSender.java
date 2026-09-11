/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.command;

import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Server;
import org.bukkit.permissions.Permissible;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandSender
extends Permissible {
    public void sendMessage(@NotNull String var1);

    public void sendMessage(String ... var1);

    public void sendMessage(@Nullable UUID var1, @NotNull String var2);

    public void sendMessage(@Nullable UUID var1, String ... var2);

    @NotNull
    public Server getServer();

    @NotNull
    public String getName();

    @NotNull
    public Spigot spigot();

    public static class Spigot {
        public void sendMessage(@NotNull BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendMessage(BaseComponent ... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendMessage(@Nullable UUID sender, @NotNull BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendMessage(@Nullable UUID sender, BaseComponent ... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

