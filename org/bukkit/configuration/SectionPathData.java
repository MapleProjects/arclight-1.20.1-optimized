/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.configuration;

import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class SectionPathData {
    private Object data;
    private List<String> comments;
    private List<String> inlineComments;

    public SectionPathData(@Nullable Object data) {
        this.data = data;
        this.comments = Collections.emptyList();
        this.inlineComments = Collections.emptyList();
    }

    @Nullable
    public Object getData() {
        return this.data;
    }

    public void setData(@Nullable Object data) {
        this.data = data;
    }

    @NotNull
    public List<String> getComments() {
        return this.comments;
    }

    public void setComments(@Nullable List<String> comments) {
        this.comments = comments == null ? Collections.emptyList() : Collections.unmodifiableList(comments);
    }

    @NotNull
    public List<String> getInlineComments() {
        return this.inlineComments;
    }

    public void setInlineComments(@Nullable List<String> inlineComments) {
        this.inlineComments = inlineComments == null ? Collections.emptyList() : Collections.unmodifiableList(inlineComments);
    }
}

