/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.util;

import java.util.Arrays;
import java.util.LinkedList;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChatPaginator {
    public static final int GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH = 55;
    public static final int AVERAGE_CHAT_PAGE_WIDTH = 65;
    public static final int UNBOUNDED_PAGE_WIDTH = Integer.MAX_VALUE;
    public static final int OPEN_CHAT_PAGE_HEIGHT = 20;
    public static final int CLOSED_CHAT_PAGE_HEIGHT = 10;
    public static final int UNBOUNDED_PAGE_HEIGHT = Integer.MAX_VALUE;

    @NotNull
    public static ChatPage paginate(@Nullable String unpaginatedString, int pageNumber) {
        return ChatPaginator.paginate(unpaginatedString, pageNumber, 55, 10);
    }

    @NotNull
    public static ChatPage paginate(@Nullable String unpaginatedString, int pageNumber, int lineLength, int pageHeight) {
        String[] lines = ChatPaginator.wordWrap(unpaginatedString, lineLength);
        int totalPages = lines.length / pageHeight + (lines.length % pageHeight == 0 ? 0 : 1);
        int actualPageNumber = pageNumber <= totalPages ? pageNumber : totalPages;
        int from = (actualPageNumber - 1) * pageHeight;
        int to = from + pageHeight <= lines.length ? from + pageHeight : lines.length;
        String[] selectedLines = Arrays.copyOfRange(lines, from, to);
        return new ChatPage(selectedLines, actualPageNumber, totalPages);
    }

    @NotNull
    public static String[] wordWrap(@Nullable String rawString, int lineLength) {
        if (rawString == null) {
            return new String[]{""};
        }
        if (rawString.length() <= lineLength && !rawString.contains("\n")) {
            return new String[]{rawString};
        }
        char[] rawChars = (String.valueOf(rawString) + ' ').toCharArray();
        StringBuilder word = new StringBuilder();
        StringBuilder line = new StringBuilder();
        LinkedList<String> lines = new LinkedList<String>();
        int lineColorChars = 0;
        int i = 0;
        while (i < rawChars.length) {
            char c = rawChars[i];
            if (c == '\u00a7') {
                word.append((Object)ChatColor.getByChar(rawChars[i + 1]));
                lineColorChars += 2;
                ++i;
            } else if (c == ' ' || c == '\n') {
                String partialWord;
                int n;
                int n2;
                String[] stringArray;
                if (line.length() == 0 && word.length() > lineLength) {
                    stringArray = word.toString().split("(?<=\\G.{" + lineLength + "})");
                    n2 = stringArray.length;
                    n = 0;
                    while (n < n2) {
                        partialWord = stringArray[n];
                        lines.add(partialWord);
                        ++n;
                    }
                } else if (line.length() + 1 + word.length() - lineColorChars == lineLength) {
                    if (line.length() > 0) {
                        line.append(' ');
                    }
                    line.append((CharSequence)word);
                    lines.add(line.toString());
                    line = new StringBuilder();
                    lineColorChars = 0;
                } else if (line.length() + 1 + word.length() - lineColorChars > lineLength) {
                    stringArray = word.toString().split("(?<=\\G.{" + lineLength + "})");
                    n2 = stringArray.length;
                    n = 0;
                    while (n < n2) {
                        partialWord = stringArray[n];
                        lines.add(line.toString());
                        line = new StringBuilder(partialWord);
                        ++n;
                    }
                    lineColorChars = 0;
                } else {
                    if (line.length() > 0) {
                        line.append(' ');
                    }
                    line.append((CharSequence)word);
                }
                word = new StringBuilder();
                if (c == '\n') {
                    lines.add(line.toString());
                    line = new StringBuilder();
                }
            } else {
                word.append(c);
            }
            ++i;
        }
        if (line.length() > 0) {
            lines.add(line.toString());
        }
        i = 1;
        while (i < lines.size()) {
            String pLine = (String)lines.get(i - 1);
            String subLine = (String)lines.get(i);
            String color = ChatColor.getLastColors(pLine);
            lines.set(i, String.valueOf(color) + subLine);
            ++i;
        }
        return lines.toArray(new String[lines.size()]);
    }

    public static class ChatPage {
        private String[] lines;
        private int pageNumber;
        private int totalPages;

        public ChatPage(@NotNull String[] lines, int pageNumber, int totalPages) {
            this.lines = lines;
            this.pageNumber = pageNumber;
            this.totalPages = totalPages;
        }

        public int getPageNumber() {
            return this.pageNumber;
        }

        public int getTotalPages() {
            return this.totalPages;
        }

        @NotNull
        public String[] getLines() {
            return this.lines;
        }
    }
}

