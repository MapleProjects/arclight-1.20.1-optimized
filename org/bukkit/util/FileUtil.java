/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import org.jetbrains.annotations.NotNull;

public class FileUtil {
    public static boolean copy(@NotNull File inFile, @NotNull File outFile) {
        if (!inFile.exists()) {
            return false;
        }
        FileChannel in = null;
        AbstractInterruptibleChannel out = null;
        try {
            try {
                in = new FileInputStream(inFile).getChannel();
                out = new FileOutputStream(outFile).getChannel();
                long pos = 0L;
                long size = in.size();
                while (pos < size) {
                    pos += in.transferTo(pos, 0xA00000L, (WritableByteChannel)((Object)out));
                }
            }
            catch (IOException ioe) {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
                catch (IOException ioe2) {
                    return false;
                }
                return false;
            }
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException ioe) {
                return false;
            }
        }
        return true;
    }
}

