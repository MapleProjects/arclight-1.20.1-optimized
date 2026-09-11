/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class WorldUUID {
    private static final Logger LOGGER = LogManager.getLogger();

    private WorldUUID() {
    }

    public static UUID getUUID(File baseDir) {
        UUID uuid;
        block26: {
            File file1 = new File(baseDir, "uid.dat");
            if (file1.exists()) {
                FilterInputStream dis = null;
                try {
                    dis = new DataInputStream(new FileInputStream(file1));
                    UUID uUID = new UUID(((DataInputStream)dis).readLong(), ((DataInputStream)dis).readLong());
                    return uUID;
                }
                catch (IOException ex) {
                    LOGGER.warn("Failed to read " + file1 + ", generating new random UUID", (Throwable)ex);
                }
                finally {
                    if (dis != null) {
                        try {
                            dis.close();
                        }
                        catch (IOException iOException) {}
                    }
                }
            }
            uuid = UUID.randomUUID();
            FilterOutputStream dos = null;
            try {
                try {
                    dos = new DataOutputStream(new FileOutputStream(file1));
                    ((DataOutputStream)dos).writeLong(uuid.getMostSignificantBits());
                    ((DataOutputStream)dos).writeLong(uuid.getLeastSignificantBits());
                }
                catch (IOException ex) {
                    LOGGER.warn("Failed to write " + file1, (Throwable)ex);
                    if (dos == null) break block26;
                    try {
                        dos.close();
                    }
                    catch (IOException iOException) {}
                }
            }
            finally {
                if (dos != null) {
                    try {
                        dos.close();
                    }
                    catch (IOException iOException) {}
                }
            }
        }
        return uuid;
    }
}

