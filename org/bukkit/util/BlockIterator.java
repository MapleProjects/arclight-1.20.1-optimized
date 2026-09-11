/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class BlockIterator
implements Iterator<Block> {
    private final World world;
    private final int maxDistance;
    private static final int gridSize = 0x1000000;
    private boolean end = false;
    private Block[] blockQueue = new Block[3];
    private int currentBlock = 0;
    private int currentDistance = 0;
    private int maxDistanceInt;
    private int secondError;
    private int thirdError;
    private int secondStep;
    private int thirdStep;
    private BlockFace mainFace;
    private BlockFace secondFace;
    private BlockFace thirdFace;

    public BlockIterator(@NotNull World world, @NotNull Vector start, @NotNull Vector direction, double yOffset, int maxDistance) {
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"world must not be null");
        Preconditions.checkArgument((start != null ? 1 : 0) != 0, (Object)"start must not be null");
        Preconditions.checkArgument((direction != null ? 1 : 0) != 0, (Object)"direction must not be null");
        Preconditions.checkArgument((!direction.isZero() ? 1 : 0) != 0, (Object)"direction must have at least one non-zero component");
        this.world = world;
        this.maxDistance = maxDistance;
        Vector startClone = start.clone();
        startClone.setY(startClone.getY() + yOffset);
        this.currentDistance = 0;
        double mainDirection = 0.0;
        double secondDirection = 0.0;
        double thirdDirection = 0.0;
        double mainPosition = 0.0;
        double secondPosition = 0.0;
        double thirdPosition = 0.0;
        Block startBlock = this.world.getBlockAt(NumberConversions.floor(startClone.getX()), NumberConversions.floor(startClone.getY()), NumberConversions.floor(startClone.getZ()));
        if (this.getXLength(direction) > mainDirection) {
            this.mainFace = this.getXFace(direction);
            mainDirection = this.getXLength(direction);
            mainPosition = this.getXPosition(direction, startClone, startBlock);
            this.secondFace = this.getYFace(direction);
            secondDirection = this.getYLength(direction);
            secondPosition = this.getYPosition(direction, startClone, startBlock);
            this.thirdFace = this.getZFace(direction);
            thirdDirection = this.getZLength(direction);
            thirdPosition = this.getZPosition(direction, startClone, startBlock);
        }
        if (this.getYLength(direction) > mainDirection) {
            this.mainFace = this.getYFace(direction);
            mainDirection = this.getYLength(direction);
            mainPosition = this.getYPosition(direction, startClone, startBlock);
            this.secondFace = this.getZFace(direction);
            secondDirection = this.getZLength(direction);
            secondPosition = this.getZPosition(direction, startClone, startBlock);
            this.thirdFace = this.getXFace(direction);
            thirdDirection = this.getXLength(direction);
            thirdPosition = this.getXPosition(direction, startClone, startBlock);
        }
        if (this.getZLength(direction) > mainDirection) {
            this.mainFace = this.getZFace(direction);
            mainDirection = this.getZLength(direction);
            mainPosition = this.getZPosition(direction, startClone, startBlock);
            this.secondFace = this.getXFace(direction);
            secondDirection = this.getXLength(direction);
            secondPosition = this.getXPosition(direction, startClone, startBlock);
            this.thirdFace = this.getYFace(direction);
            thirdDirection = this.getYLength(direction);
            thirdPosition = this.getYPosition(direction, startClone, startBlock);
        }
        double d = mainPosition / mainDirection;
        double secondd = secondPosition - secondDirection * d;
        double thirdd = thirdPosition - thirdDirection * d;
        this.secondError = NumberConversions.floor(secondd * 1.6777216E7);
        this.secondStep = NumberConversions.round(secondDirection / mainDirection * 1.6777216E7);
        this.thirdError = NumberConversions.floor(thirdd * 1.6777216E7);
        this.thirdStep = NumberConversions.round(thirdDirection / mainDirection * 1.6777216E7);
        if (this.secondError + this.secondStep <= 0) {
            this.secondError = -this.secondStep + 1;
        }
        if (this.thirdError + this.thirdStep <= 0) {
            this.thirdError = -this.thirdStep + 1;
        }
        Block lastBlock = startBlock.getRelative(this.mainFace.getOppositeFace());
        if (this.secondError < 0) {
            this.secondError += 0x1000000;
            lastBlock = lastBlock.getRelative(this.secondFace.getOppositeFace());
        }
        if (this.thirdError < 0) {
            this.thirdError += 0x1000000;
            lastBlock = lastBlock.getRelative(this.thirdFace.getOppositeFace());
        }
        this.secondError -= 0x1000000;
        this.thirdError -= 0x1000000;
        this.blockQueue[0] = lastBlock;
        this.currentBlock = -1;
        this.scan();
        boolean startBlockFound = false;
        int cnt = this.currentBlock;
        while (cnt >= 0) {
            if (this.blockEquals(this.blockQueue[cnt], startBlock)) {
                this.currentBlock = cnt;
                startBlockFound = true;
                break;
            }
            --cnt;
        }
        if (!startBlockFound) {
            throw new IllegalStateException("Start block missed in BlockIterator");
        }
        this.maxDistanceInt = NumberConversions.round((double)maxDistance / (Math.sqrt(mainDirection * mainDirection + secondDirection * secondDirection + thirdDirection * thirdDirection) / mainDirection));
    }

    private boolean blockEquals(@NotNull Block a, @NotNull Block b) {
        return a.getX() == b.getX() && a.getY() == b.getY() && a.getZ() == b.getZ();
    }

    private BlockFace getXFace(@NotNull Vector direction) {
        return direction.getX() > 0.0 ? BlockFace.EAST : BlockFace.WEST;
    }

    private BlockFace getYFace(@NotNull Vector direction) {
        return direction.getY() > 0.0 ? BlockFace.UP : BlockFace.DOWN;
    }

    private BlockFace getZFace(@NotNull Vector direction) {
        return direction.getZ() > 0.0 ? BlockFace.SOUTH : BlockFace.NORTH;
    }

    private double getXLength(@NotNull Vector direction) {
        return Math.abs(direction.getX());
    }

    private double getYLength(@NotNull Vector direction) {
        return Math.abs(direction.getY());
    }

    private double getZLength(@NotNull Vector direction) {
        return Math.abs(direction.getZ());
    }

    private double getPosition(double direction, double position, int blockPosition) {
        return direction > 0.0 ? position - (double)blockPosition : (double)(blockPosition + 1) - position;
    }

    private double getXPosition(@NotNull Vector direction, @NotNull Vector position, @NotNull Block block) {
        return this.getPosition(direction.getX(), position.getX(), block.getX());
    }

    private double getYPosition(@NotNull Vector direction, @NotNull Vector position, @NotNull Block block) {
        return this.getPosition(direction.getY(), position.getY(), block.getY());
    }

    private double getZPosition(@NotNull Vector direction, @NotNull Vector position, @NotNull Block block) {
        return this.getPosition(direction.getZ(), position.getZ(), block.getZ());
    }

    public BlockIterator(@NotNull Location loc, double yOffset, int maxDistance) {
        this(loc.getWorld(), loc.toVector(), loc.getDirection(), yOffset, maxDistance);
    }

    public BlockIterator(@NotNull Location loc, double yOffset) {
        this(loc.getWorld(), loc.toVector(), loc.getDirection(), yOffset, 0);
    }

    public BlockIterator(@NotNull Location loc) {
        this(loc, 0.0);
    }

    public BlockIterator(@NotNull LivingEntity entity, int maxDistance) {
        this(entity.getLocation(), entity.getEyeHeight(), maxDistance);
    }

    public BlockIterator(@NotNull LivingEntity entity) {
        this(entity, 0);
    }

    @Override
    public boolean hasNext() {
        this.scan();
        return this.currentBlock != -1;
    }

    @Override
    @NotNull
    public Block next() throws NoSuchElementException {
        this.scan();
        if (this.currentBlock <= -1) {
            throw new NoSuchElementException();
        }
        return this.blockQueue[this.currentBlock--];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("[BlockIterator] doesn't support block removal");
    }

    private void scan() {
        if (this.currentBlock >= 0) {
            return;
        }
        if (this.maxDistance != 0 && this.currentDistance > this.maxDistanceInt) {
            this.end = true;
            return;
        }
        if (this.end) {
            return;
        }
        ++this.currentDistance;
        this.secondError += this.secondStep;
        this.thirdError += this.thirdStep;
        if (this.secondError > 0 && this.thirdError > 0) {
            this.blockQueue[2] = this.blockQueue[0].getRelative(this.mainFace);
            if ((long)this.secondStep * (long)this.thirdError < (long)this.thirdStep * (long)this.secondError) {
                this.blockQueue[1] = this.blockQueue[2].getRelative(this.secondFace);
                this.blockQueue[0] = this.blockQueue[1].getRelative(this.thirdFace);
            } else {
                this.blockQueue[1] = this.blockQueue[2].getRelative(this.thirdFace);
                this.blockQueue[0] = this.blockQueue[1].getRelative(this.secondFace);
            }
            this.thirdError -= 0x1000000;
            this.secondError -= 0x1000000;
            this.currentBlock = 2;
            return;
        }
        if (this.secondError > 0) {
            this.blockQueue[1] = this.blockQueue[0].getRelative(this.mainFace);
            this.blockQueue[0] = this.blockQueue[1].getRelative(this.secondFace);
            this.secondError -= 0x1000000;
            this.currentBlock = 1;
            return;
        }
        if (this.thirdError > 0) {
            this.blockQueue[1] = this.blockQueue[0].getRelative(this.mainFace);
            this.blockQueue[0] = this.blockQueue[1].getRelative(this.thirdFace);
            this.thirdError -= 0x1000000;
            this.currentBlock = 1;
            return;
        }
        this.blockQueue[0] = this.blockQueue[0].getRelative(this.mainFace);
        this.currentBlock = 0;
    }
}

