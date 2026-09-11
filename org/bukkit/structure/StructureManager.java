/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.structure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import org.bukkit.NamespacedKey;
import org.bukkit.structure.Structure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructureManager {
    @NotNull
    public Map<NamespacedKey, Structure> getStructures();

    @Nullable
    public Structure getStructure(@NotNull NamespacedKey var1);

    @Nullable
    public Structure registerStructure(@NotNull NamespacedKey var1, @NotNull Structure var2);

    @Nullable
    public Structure unregisterStructure(@NotNull NamespacedKey var1);

    @Nullable
    public Structure loadStructure(@NotNull NamespacedKey var1, boolean var2);

    @Nullable
    public Structure loadStructure(@NotNull NamespacedKey var1);

    public void saveStructure(@NotNull NamespacedKey var1);

    public void saveStructure(@NotNull NamespacedKey var1, @NotNull Structure var2) throws IOException;

    public void deleteStructure(@NotNull NamespacedKey var1) throws IOException;

    public void deleteStructure(@NotNull NamespacedKey var1, boolean var2) throws IOException;

    @NotNull
    public File getStructureFile(@NotNull NamespacedKey var1);

    @NotNull
    public Structure loadStructure(@NotNull File var1) throws IOException;

    @NotNull
    public Structure loadStructure(@NotNull InputStream var1) throws IOException;

    public void saveStructure(@NotNull File var1, @NotNull Structure var2) throws IOException;

    public void saveStructure(@NotNull OutputStream var1, @NotNull Structure var2) throws IOException;

    @NotNull
    public Structure createStructure();

    @NotNull
    public Structure copy(@NotNull Structure var1);
}

