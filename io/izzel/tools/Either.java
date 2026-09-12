/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Either<A, B> {
    public abstract Optional<A> left();

    public abstract Optional<B> right();

    public abstract <C, D> Either<C, D> map(Function<? super A, ? extends C> var1, Function<? super B, ? extends D> var2);

    public abstract <C, D> Either<C, D> flatMap(Function<? super A, ? extends Either<C, D>> var1, Function<? super B, ? extends Either<C, D>> var2);

    public abstract <T> T fold(Function<? super A, ? extends T> var1, Function<? super B, ? extends T> var2);

    public abstract Either<A, B> ifLeft(Consumer<? super A> var1);

    public abstract Either<A, B> ifRight(Consumer<? super B> var1);

    public final boolean isLeft() {
        return this.left().isPresent();
    }

    public final boolean isRight() {
        return this.right().isPresent();
    }

    public final Either<B, A> swap() {
        return this.fold(Either::right, Either::left);
    }

    public final <C> Either<C, B> mapLeft(Function<? super A, ? extends C> left) {
        return this.map(left, Function.identity());
    }

    public final <D> Either<A, D> mapRight(Function<? super B, ? extends D> right) {
        return this.map(Function.identity(), right);
    }

    public static <A, B> Either<A, B> left(A left) {
        return new Left(left);
    }

    public static <A, B> Either<A, B> right(B right) {
        return new Right(right);
    }

    private static final class Right<A, B>
    extends Either<A, B> {
        private final B right;

        private Right(B right) {
            Objects.requireNonNull(right, "right");
            this.right = right;
        }

        @Override
        public Optional<A> left() {
            return Optional.empty();
        }

        @Override
        public Optional<B> right() {
            return Optional.of(this.right);
        }

        @Override
        public <C, D> Either<C, D> map(Function<? super A, ? extends C> left, Function<? super B, ? extends D> right) {
            return new Right<A, D>(right.apply(this.right));
        }

        @Override
        public <C, D> Either<C, D> flatMap(Function<? super A, ? extends Either<C, D>> left, Function<? super B, ? extends Either<C, D>> right) {
            return right.apply(this.right);
        }

        @Override
        public <T> T fold(Function<? super A, ? extends T> left, Function<? super B, ? extends T> right) {
            return right.apply(this.right);
        }

        @Override
        public Either<A, B> ifLeft(Consumer<? super A> consumer) {
            return this;
        }

        @Override
        public Either<A, B> ifRight(Consumer<? super B> consumer) {
            consumer.accept(this.right);
            return this;
        }

        public String toString() {
            return "Right[" + this.right + ']';
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Right right1 = (Right)o;
            return Objects.equals(this.right, right1.right);
        }

        public int hashCode() {
            return this.right.hashCode();
        }
    }

    private static final class Left<A, B>
    extends Either<A, B> {
        private final A left;

        private Left(A left) {
            Objects.requireNonNull(left, "left");
            this.left = left;
        }

        @Override
        public Optional<A> left() {
            return Optional.of(this.left);
        }

        @Override
        public Optional<B> right() {
            return Optional.empty();
        }

        @Override
        public <C, D> Either<C, D> map(Function<? super A, ? extends C> left, Function<? super B, ? extends D> right) {
            return new Left<C, B>(left.apply(this.left));
        }

        @Override
        public <C, D> Either<C, D> flatMap(Function<? super A, ? extends Either<C, D>> left, Function<? super B, ? extends Either<C, D>> right) {
            return left.apply(this.left);
        }

        @Override
        public <T> T fold(Function<? super A, ? extends T> left, Function<? super B, ? extends T> right) {
            return left.apply(this.left);
        }

        @Override
        public Either<A, B> ifLeft(Consumer<? super A> consumer) {
            consumer.accept(this.left);
            return this;
        }

        @Override
        public Either<A, B> ifRight(Consumer<? super B> consumer) {
            return this;
        }

        public String toString() {
            return "Left[" + this.left + ']';
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Left left1 = (Left)o;
            return Objects.equals(this.left, left1.left);
        }

        public int hashCode() {
            return this.left.hashCode();
        }
    }
}

