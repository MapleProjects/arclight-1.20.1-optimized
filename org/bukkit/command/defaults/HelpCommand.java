/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.command.defaults;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.util.ChatPaginator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HelpCommand
extends BukkitCommand {
    public HelpCommand() {
        super("help");
        this.description = "Shows the help menu";
        this.usageMessage = "/help <pageNumber>\n/help <topic>\n/help <topic> <pageNumber>";
        this.setAliases(Arrays.asList("?"));
        this.setPermission("bukkit.command.help");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
        int pageWidth;
        int pageHeight;
        int pageNumber;
        String command;
        if (!this.testPermission(sender)) {
            return true;
        }
        if (args.length == 0) {
            command = "";
            pageNumber = 1;
        } else if (args[args.length - 1].chars().allMatch(Character::isDigit)) {
            command = Joiner.on((String)" ").join((Object[])Arrays.copyOfRange(args, 0, args.length - 1));
            try {
                pageNumber = Integer.decode(args[args.length - 1]);
            }
            catch (NumberFormatException exception) {
                pageNumber = 1;
            }
            if (pageNumber <= 0) {
                pageNumber = 1;
            }
        } else {
            command = Joiner.on((String)" ").join((Object[])args);
            pageNumber = 1;
        }
        if (sender instanceof ConsoleCommandSender) {
            pageHeight = Integer.MAX_VALUE;
            pageWidth = Integer.MAX_VALUE;
        } else {
            pageHeight = 9;
            pageWidth = 55;
        }
        HelpMap helpMap = Bukkit.getServer().getHelpMap();
        HelpTopic topic = helpMap.getHelpTopic(command);
        if (topic == null) {
            topic = helpMap.getHelpTopic("/" + command);
        }
        if (topic == null) {
            topic = this.findPossibleMatches(command);
        }
        if (topic == null || !topic.canSee(sender)) {
            sender.sendMessage((Object)((Object)ChatColor.RED) + "No help for " + command);
            return true;
        }
        ChatPaginator.ChatPage page = ChatPaginator.paginate(topic.getFullText(sender), pageNumber, pageWidth, pageHeight);
        StringBuilder header = new StringBuilder();
        header.append((Object)ChatColor.YELLOW);
        header.append("--------- ");
        header.append((Object)ChatColor.WHITE);
        header.append("Help: ");
        header.append(topic.getName());
        header.append(" ");
        if (page.getTotalPages() > 1) {
            header.append("(");
            header.append(page.getPageNumber());
            header.append("/");
            header.append(page.getTotalPages());
            header.append(") ");
        }
        header.append((Object)ChatColor.YELLOW);
        int i = header.length();
        while (i < 55) {
            header.append("-");
            ++i;
        }
        sender.sendMessage(header.toString());
        sender.sendMessage(page.getLines());
        return true;
    }

    @Override
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"Sender cannot be null");
        Preconditions.checkArgument((args != null ? 1 : 0) != 0, (Object)"Arguments cannot be null");
        Preconditions.checkArgument((alias != null ? 1 : 0) != 0, (Object)"Alias cannot be null");
        if (args.length == 1) {
            ArrayList<String> matchedTopics = new ArrayList<String>();
            String searchString = args[0];
            for (HelpTopic topic : Bukkit.getServer().getHelpMap().getHelpTopics()) {
                String trimmedTopic;
                if (!topic.canSee(sender)) continue;
                String string = trimmedTopic = topic.getName().startsWith("/") ? topic.getName().substring(1) : topic.getName();
                if (!trimmedTopic.startsWith(searchString)) continue;
                matchedTopics.add(trimmedTopic);
            }
            return matchedTopics;
        }
        return ImmutableList.of();
    }

    @Nullable
    protected HelpTopic findPossibleMatches(@NotNull String searchString) {
        int maxDistance = searchString.length() / 5 + 3;
        TreeSet<HelpTopic> possibleMatches = new TreeSet<HelpTopic>(HelpTopicComparator.helpTopicComparatorInstance());
        if (searchString.startsWith("/")) {
            searchString = searchString.substring(1);
        }
        for (HelpTopic topic : Bukkit.getServer().getHelpMap().getHelpTopics()) {
            String trimmedTopic;
            String string = trimmedTopic = topic.getName().startsWith("/") ? topic.getName().substring(1) : topic.getName();
            if (trimmedTopic.length() < searchString.length() || Character.toLowerCase(trimmedTopic.charAt(0)) != Character.toLowerCase(searchString.charAt(0)) || HelpCommand.damerauLevenshteinDistance(searchString, trimmedTopic.substring(0, searchString.length())) >= maxDistance) continue;
            possibleMatches.add(topic);
        }
        if (possibleMatches.size() > 0) {
            return new IndexHelpTopic("Search", null, null, possibleMatches, "Search for: " + searchString);
        }
        return null;
    }

    protected static int damerauLevenshteinDistance(@Nullable String s1, @Nullable String s2) {
        int INF;
        if (s1 == null && s2 == null) {
            return 0;
        }
        if (s1 != null && s2 == null) {
            return s1.length();
        }
        if (s1 == null && s2 != null) {
            return s2.length();
        }
        int s1Len = s1.length();
        int s2Len = s2.length();
        int[][] H = new int[s1Len + 2][s2Len + 2];
        H[0][0] = INF = s1Len + s2Len;
        int i = 0;
        while (i <= s1Len) {
            H[i + 1][1] = i;
            H[i + 1][0] = INF;
            ++i;
        }
        int j = 0;
        while (j <= s2Len) {
            H[1][j + 1] = j;
            H[0][j + 1] = INF;
            ++j;
        }
        HashMap<Character, Integer> sd = new HashMap<Character, Integer>();
        char[] cArray = (String.valueOf(s1) + s2).toCharArray();
        int n = cArray.length;
        int n2 = 0;
        while (n2 < n) {
            char Letter = cArray[n2];
            if (!sd.containsKey(Character.valueOf(Letter))) {
                sd.put(Character.valueOf(Letter), 0);
            }
            ++n2;
        }
        int i2 = 1;
        while (i2 <= s1Len) {
            int DB = 0;
            int j2 = 1;
            while (j2 <= s2Len) {
                int i1 = (Integer)sd.get(Character.valueOf(s2.charAt(j2 - 1)));
                int j1 = DB;
                if (s1.charAt(i2 - 1) == s2.charAt(j2 - 1)) {
                    H[i2 + 1][j2 + 1] = H[i2][j2];
                    DB = j2;
                } else {
                    H[i2 + 1][j2 + 1] = Math.min(H[i2][j2], Math.min(H[i2 + 1][j2], H[i2][j2 + 1])) + 1;
                }
                H[i2 + 1][j2 + 1] = Math.min(H[i2 + 1][j2 + 1], H[i1][j1] + (i2 - i1 - 1) + 1 + (j2 - j1 - 1));
                ++j2;
            }
            sd.put(Character.valueOf(s1.charAt(i2 - 1)), i2);
            ++i2;
        }
        return H[s1Len + 1][s2Len + 1];
    }
}

