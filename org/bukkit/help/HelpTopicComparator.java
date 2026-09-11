/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.help;

import java.util.Comparator;
import org.bukkit.help.HelpTopic;
import org.jetbrains.annotations.NotNull;

public final class HelpTopicComparator
implements Comparator<HelpTopic> {
    private static final TopicNameComparator tnc = new TopicNameComparator();
    private static final HelpTopicComparator htc = new HelpTopicComparator();

    @NotNull
    public static TopicNameComparator topicNameComparatorInstance() {
        return tnc;
    }

    @NotNull
    public static HelpTopicComparator helpTopicComparatorInstance() {
        return htc;
    }

    private HelpTopicComparator() {
    }

    @Override
    public int compare(@NotNull HelpTopic lhs, @NotNull HelpTopic rhs) {
        return tnc.compare(lhs.getName(), rhs.getName());
    }

    public static final class TopicNameComparator
    implements Comparator<String> {
        private TopicNameComparator() {
        }

        @Override
        public int compare(@NotNull String lhs, @NotNull String rhs) {
            boolean lhsStartSlash = lhs.startsWith("/");
            boolean rhsStartSlash = rhs.startsWith("/");
            if (lhsStartSlash && !rhsStartSlash) {
                return 1;
            }
            if (!lhsStartSlash && rhsStartSlash) {
                return -1;
            }
            return lhs.compareToIgnoreCase(rhs);
        }
    }
}

