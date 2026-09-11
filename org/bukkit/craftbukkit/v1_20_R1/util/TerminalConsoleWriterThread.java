/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.logging.LogQueues
 *  jline.console.ConsoleReader
 *  org.fusesource.jansi.Ansi
 *  org.fusesource.jansi.Ansi$Erase
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import com.mojang.logging.LogQueues;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.console.ConsoleReader;
import org.bukkit.craftbukkit.Main;
import org.fusesource.jansi.Ansi;

public class TerminalConsoleWriterThread
extends Thread {
    private final ConsoleReader reader;
    private final OutputStream output;

    public TerminalConsoleWriterThread(OutputStream output, ConsoleReader reader) {
        super("TerminalConsoleWriter");
        this.output = output;
        this.reader = reader;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            String message;
            if ((message = LogQueues.getNextLogEvent((String)"TerminalConsole")) == null) {
                continue;
            }
            try {
                if (Main.useJline) {
                    this.reader.print((CharSequence)(String.valueOf(Ansi.ansi().eraseLine(Ansi.Erase.ALL).toString()) + '\r'));
                    this.reader.flush();
                    this.output.write(message.getBytes());
                    this.output.flush();
                    try {
                        this.reader.drawLine();
                    }
                    catch (Throwable ex) {
                        this.reader.getCursorBuffer().clear();
                    }
                    this.reader.flush();
                    continue;
                }
                this.output.write(message.getBytes());
                this.output.flush();
                continue;
            }
            catch (IOException ex) {
                Logger.getLogger(TerminalConsoleWriterThread.class.getName()).log(Level.SEVERE, null, ex);
                continue;
            }
            break;
        }
    }
}

