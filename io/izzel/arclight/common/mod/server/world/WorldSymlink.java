/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.storage.DerivedLevelData
 */
package io.izzel.arclight.common.mod.server.world;

import io.izzel.arclight.common.mod.ArclightMod;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import net.minecraft.world.level.storage.DerivedLevelData;
import org.bukkit.Bukkit;

public class WorldSymlink {
    public static void create(DerivedLevelData worldInfo, File dimensionFolder) {
        String name = worldInfo.m_5462_();
        Path source = new File(Bukkit.getWorldContainer(), name).toPath();
        Path dest = dimensionFolder.toPath();
        try {
            if (!Files.isSymbolicLink(source)) {
                if (Files.exists(source, new LinkOption[0])) {
                    ArclightMod.LOGGER.warn("symlink-file-exist", (Object)source);
                    return;
                }
                Files.createSymbolicLink(source, dest, new FileAttribute[0]);
            }
        }
        catch (UnsupportedOperationException e) {
            ArclightMod.LOGGER.warn("error-symlink", (Throwable)e);
        }
        catch (IOException e) {
            ArclightMod.LOGGER.error("Error creating symlink", (Throwable)e);
        }
    }
}

