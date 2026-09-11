/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.command;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class FormattedCommandAlias
extends Command {
    private final String[] formatStrings;

    public FormattedCommandAlias(@NotNull String alias, @NotNull String[] formatStrings) {
        super(alias);
        this.formatStrings = formatStrings;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        boolean result = false;
        ArrayList<String> commands = new ArrayList<String>();
        String[] stringArray = this.formatStrings;
        int n = this.formatStrings.length;
        int n2 = 0;
        while (n2 < n) {
            String formatString = stringArray[n2];
            try {
                commands.add(this.buildCommand(formatString, args));
            }
            catch (Throwable throwable) {
                if (throwable instanceof IllegalArgumentException) {
                    sender.sendMessage(throwable.getMessage());
                } else {
                    sender.sendMessage((Object)((Object)ChatColor.RED) + "An internal error occurred while attempting to perform this command");
                }
                return false;
            }
            ++n2;
        }
        for (String command : commands) {
            result |= Bukkit.dispatchCommand(sender, command);
        }
        return result;
    }

    private String buildCommand(@NotNull String formatString, @NotNull String[] args) {
        int index = formatString.indexOf(36);
        while (index != -1) {
            int start = index;
            if (index > 0 && formatString.charAt(start - 1) == '\\') {
                formatString = String.valueOf(formatString.substring(0, start - 1)) + formatString.substring(start);
                index = formatString.indexOf(36, index);
                continue;
            }
            boolean required = false;
            if (formatString.charAt(index + 1) == '$') {
                required = true;
                ++index;
            }
            int argStart = ++index;
            while (index < formatString.length() && FormattedCommandAlias.inRange(formatString.charAt(index) - 48, 0, 9)) {
                ++index;
            }
            if (argStart == index) {
                throw new IllegalArgumentException("Invalid replacement token");
            }
            int position = Integer.parseInt(formatString.substring(argStart, index));
            if (position == 0) {
                throw new IllegalArgumentException("Invalid replacement token");
            }
            --position;
            boolean rest = false;
            if (index < formatString.length() && formatString.charAt(index) == '-') {
                rest = true;
            }
            int end = ++index;
            if (required && position >= args.length) {
                throw new IllegalArgumentException("Missing required argument " + (position + 1));
            }
            StringBuilder replacement = new StringBuilder();
            if (rest && position < args.length) {
                int i = position;
                while (i < args.length) {
                    if (i != position) {
                        replacement.append(' ');
                    }
                    replacement.append(args[i]);
                    ++i;
                }
            } else if (position < args.length) {
                replacement.append(args[position]);
            }
            formatString = String.valueOf(formatString.substring(0, start)) + replacement.toString() + formatString.substring(end);
            index = start + replacement.length();
            index = formatString.indexOf(36, index);
        }
        return formatString;
    }

    private static boolean inRange(int i, int j, int k) {
        return i >= j && i <= k;
    }
}

