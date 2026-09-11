/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Predicates
 *  com.google.common.collect.Lists
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ChunkMap$TrackedEntity
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.AreaEffectCloud
 *  net.minecraft.world.entity.Display
 *  net.minecraft.world.entity.Display$BlockDisplay
 *  net.minecraft.world.entity.Display$ItemDisplay
 *  net.minecraft.world.entity.Display$TextDisplay
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.FlyingMob
 *  net.minecraft.world.entity.GlowSquid
 *  net.minecraft.world.entity.Interaction
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Marker
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ambient.AmbientCreature
 *  net.minecraft.world.entity.ambient.Bat
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.AbstractGolem
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Bee
 *  net.minecraft.world.entity.animal.Cat
 *  net.minecraft.world.entity.animal.Chicken
 *  net.minecraft.world.entity.animal.Cod
 *  net.minecraft.world.entity.animal.Cow
 *  net.minecraft.world.entity.animal.Dolphin
 *  net.minecraft.world.entity.animal.Fox
 *  net.minecraft.world.entity.animal.IronGolem
 *  net.minecraft.world.entity.animal.MushroomCow
 *  net.minecraft.world.entity.animal.Ocelot
 *  net.minecraft.world.entity.animal.Panda
 *  net.minecraft.world.entity.animal.Parrot
 *  net.minecraft.world.entity.animal.Pig
 *  net.minecraft.world.entity.animal.PolarBear
 *  net.minecraft.world.entity.animal.Pufferfish
 *  net.minecraft.world.entity.animal.Rabbit
 *  net.minecraft.world.entity.animal.Salmon
 *  net.minecraft.world.entity.animal.Sheep
 *  net.minecraft.world.entity.animal.SnowGolem
 *  net.minecraft.world.entity.animal.Squid
 *  net.minecraft.world.entity.animal.TropicalFish
 *  net.minecraft.world.entity.animal.Turtle
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.animal.Wolf
 *  net.minecraft.world.entity.animal.allay.Allay
 *  net.minecraft.world.entity.animal.axolotl.Axolotl
 *  net.minecraft.world.entity.animal.camel.Camel
 *  net.minecraft.world.entity.animal.frog.Frog
 *  net.minecraft.world.entity.animal.frog.Tadpole
 *  net.minecraft.world.entity.animal.goat.Goat
 *  net.minecraft.world.entity.animal.horse.AbstractChestedHorse
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 *  net.minecraft.world.entity.animal.horse.Donkey
 *  net.minecraft.world.entity.animal.horse.Horse
 *  net.minecraft.world.entity.animal.horse.Llama
 *  net.minecraft.world.entity.animal.horse.Mule
 *  net.minecraft.world.entity.animal.horse.SkeletonHorse
 *  net.minecraft.world.entity.animal.horse.TraderLlama
 *  net.minecraft.world.entity.animal.horse.ZombieHorse
 *  net.minecraft.world.entity.animal.sniffer.Sniffer
 *  net.minecraft.world.entity.boss.EnderDragonPart
 *  net.minecraft.world.entity.boss.enderdragon.EndCrystal
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.boss.wither.WitherBoss
 *  net.minecraft.world.entity.decoration.ArmorStand
 *  net.minecraft.world.entity.decoration.GlowItemFrame
 *  net.minecraft.world.entity.decoration.HangingEntity
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.entity.decoration.LeashFenceKnotEntity
 *  net.minecraft.world.entity.decoration.Painting
 *  net.minecraft.world.entity.item.FallingBlockEntity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.entity.monster.AbstractIllager
 *  net.minecraft.world.entity.monster.AbstractSkeleton
 *  net.minecraft.world.entity.monster.Blaze
 *  net.minecraft.world.entity.monster.CaveSpider
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.entity.monster.Drowned
 *  net.minecraft.world.entity.monster.ElderGuardian
 *  net.minecraft.world.entity.monster.EnderMan
 *  net.minecraft.world.entity.monster.Endermite
 *  net.minecraft.world.entity.monster.Evoker
 *  net.minecraft.world.entity.monster.Ghast
 *  net.minecraft.world.entity.monster.Giant
 *  net.minecraft.world.entity.monster.Guardian
 *  net.minecraft.world.entity.monster.Husk
 *  net.minecraft.world.entity.monster.Illusioner
 *  net.minecraft.world.entity.monster.MagmaCube
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Phantom
 *  net.minecraft.world.entity.monster.Pillager
 *  net.minecraft.world.entity.monster.Ravager
 *  net.minecraft.world.entity.monster.Shulker
 *  net.minecraft.world.entity.monster.Silverfish
 *  net.minecraft.world.entity.monster.Skeleton
 *  net.minecraft.world.entity.monster.Slime
 *  net.minecraft.world.entity.monster.SpellcasterIllager
 *  net.minecraft.world.entity.monster.Spider
 *  net.minecraft.world.entity.monster.Stray
 *  net.minecraft.world.entity.monster.Strider
 *  net.minecraft.world.entity.monster.Vex
 *  net.minecraft.world.entity.monster.Vindicator
 *  net.minecraft.world.entity.monster.Witch
 *  net.minecraft.world.entity.monster.WitherSkeleton
 *  net.minecraft.world.entity.monster.Zoglin
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.monster.ZombieVillager
 *  net.minecraft.world.entity.monster.ZombifiedPiglin
 *  net.minecraft.world.entity.monster.hoglin.Hoglin
 *  net.minecraft.world.entity.monster.piglin.AbstractPiglin
 *  net.minecraft.world.entity.monster.piglin.Piglin
 *  net.minecraft.world.entity.monster.piglin.PiglinBrute
 *  net.minecraft.world.entity.monster.warden.Warden
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.entity.npc.WanderingTrader
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.Arrow
 *  net.minecraft.world.entity.projectile.DragonFireball
 *  net.minecraft.world.entity.projectile.EvokerFangs
 *  net.minecraft.world.entity.projectile.EyeOfEnder
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.entity.projectile.FishingHook
 *  net.minecraft.world.entity.projectile.LargeFireball
 *  net.minecraft.world.entity.projectile.LlamaSpit
 *  net.minecraft.world.entity.projectile.ShulkerBullet
 *  net.minecraft.world.entity.projectile.SmallFireball
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.entity.projectile.SpectralArrow
 *  net.minecraft.world.entity.projectile.ThrowableProjectile
 *  net.minecraft.world.entity.projectile.ThrownEgg
 *  net.minecraft.world.entity.projectile.ThrownEnderpearl
 *  net.minecraft.world.entity.projectile.ThrownExperienceBottle
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.entity.projectile.ThrownTrident
 *  net.minecraft.world.entity.projectile.WitherSkull
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.entity.vehicle.ChestBoat
 *  net.minecraft.world.entity.vehicle.Minecart
 *  net.minecraft.world.entity.vehicle.MinecartChest
 *  net.minecraft.world.entity.vehicle.MinecartCommandBlock
 *  net.minecraft.world.entity.vehicle.MinecartFurnace
 *  net.minecraft.world.entity.vehicle.MinecartHopper
 *  net.minecraft.world.entity.vehicle.MinecartSpawner
 *  net.minecraft.world.entity.vehicle.MinecartTNT
 *  net.minecraft.world.phys.AABB
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.entity.vehicle.MinecartHopper;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.phys.AABB;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Registry;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftSound;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractVillager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAllay;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAmbient;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAreaEffectCloud;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAxolotl;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftBat;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftBee;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftBlaze;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftBlockDisplay;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftBoat;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCamel;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCat;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCaveSpider;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftChestBoat;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftChicken;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCod;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftComplexPart;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCow;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftDisplay;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftDolphin;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftDonkey;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftDragonFireball;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftDrowned;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEgg;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftElderGuardian;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderCrystal;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderDragon;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderDragonPart;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderPearl;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderSignal;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderman;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEndermite;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEvoker;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEvokerFangs;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftExperienceOrb;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFallingBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFireball;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFirework;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFish;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFishHook;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFlying;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFox;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFrog;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGhast;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGiant;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGlowItemFrame;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGlowSquid;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGoat;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGuardian;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHanging;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHoglin;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHorse;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHusk;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftIllager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftIllusioner;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftInteraction;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftIronGolem;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftItemDisplay;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftItemFrame;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLargeFireball;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLeash;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLightningStrike;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLlama;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLlamaSpit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMagmaCube;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMarker;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartChest;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartCommand;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartFurnace;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartHopper;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartMobSpawner;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartRideable;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartTNT;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMule;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMushroomCow;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftOcelot;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPainting;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPanda;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftParrot;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPhantom;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPig;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPigZombie;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPiglin;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPiglinAbstract;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPiglinBrute;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPillager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPolarBear;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPufferFish;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftRabbit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftRavager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSalmon;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSheep;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftShulker;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftShulkerBullet;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSilverfish;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSkeleton;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSkeletonHorse;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSlime;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSmallFireball;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSniffer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSnowball;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSnowman;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSpectralArrow;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSpellcaster;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSpider;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSquid;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftStray;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftStrider;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTNTPrimed;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTadpole;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTextDisplay;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftThrownExpBottle;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftThrownPotion;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTippedArrow;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTraderLlama;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTrident;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTropicalFish;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTurtle;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVex;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVillagerZombie;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVindicator;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWanderingTrader;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWarden;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWaterMob;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWitch;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWither;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWitherSkeleton;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWitherSkull;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWolf;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftZoglin;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftZombieHorse;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftSpawnCategory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftVector;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pose;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;
import org.spigotmc.AsyncCatcher;

public abstract class CraftEntity
implements Entity {
    private static PermissibleBase perm;
    private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY;
    protected final CraftServer server;
    protected net.minecraft.world.entity.Entity entity;
    private final org.bukkit.entity.EntityType entityType;
    private EntityDamageEvent lastDamageEvent;
    private final CraftPersistentDataContainer persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);
    private final Entity.Spigot spigot = new Entity.Spigot(){

        @Override
        public void sendMessage(BaseComponent component) {
        }

        @Override
        public void sendMessage(BaseComponent ... components) {
        }

        @Override
        public void sendMessage(UUID sender, BaseComponent ... components) {
        }

        @Override
        public void sendMessage(UUID sender, BaseComponent component) {
        }
    };

    static {
        DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
    }

    public CraftEntity(CraftServer server, net.minecraft.world.entity.Entity entity) {
        this.server = server;
        this.entity = entity;
        org.bukkit.entity.EntityType type = Registry.ENTITY_TYPE.get(CraftNamespacedKey.fromMinecraft(EntityType.m_20613_((EntityType)entity.m_6095_())));
        this.entityType = type != null ? type : org.bukkit.entity.EntityType.UNKNOWN;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static CraftEntity getEntity(CraftServer server, net.minecraft.world.entity.Entity entity) {
        if (entity instanceof LivingEntity) {
            if (entity instanceof Player) {
                if (!(entity instanceof ServerPlayer)) return new CraftHumanEntity(server, (Player)entity);
                return new CraftPlayer(server, (ServerPlayer)entity);
            }
            if (entity instanceof WaterAnimal) {
                if (entity instanceof Squid) {
                    if (!(entity instanceof GlowSquid)) return new CraftSquid(server, (Squid)entity);
                    return new CraftGlowSquid(server, (GlowSquid)entity);
                }
                if (entity instanceof AbstractFish) {
                    if (entity instanceof Cod) {
                        return new CraftCod(server, (Cod)entity);
                    }
                    if (entity instanceof Pufferfish) {
                        return new CraftPufferFish(server, (Pufferfish)entity);
                    }
                    if (entity instanceof Salmon) {
                        return new CraftSalmon(server, (Salmon)entity);
                    }
                    if (entity instanceof TropicalFish) {
                        return new CraftTropicalFish(server, (TropicalFish)entity);
                    }
                    if (!(entity instanceof Tadpole)) return new CraftFish(server, (AbstractFish)entity);
                    return new CraftTadpole(server, (Tadpole)entity);
                }
                if (!(entity instanceof Dolphin)) return new CraftWaterMob(server, (WaterAnimal)entity);
                return new CraftDolphin(server, (Dolphin)entity);
            }
            if (entity instanceof PathfinderMob) {
                if (entity instanceof Animal) {
                    if (entity instanceof Chicken) {
                        return new CraftChicken(server, (Chicken)entity);
                    }
                    if (entity instanceof Cow) {
                        if (!(entity instanceof MushroomCow)) return new CraftCow(server, (Cow)entity);
                        return new CraftMushroomCow(server, (MushroomCow)entity);
                    }
                    if (entity instanceof Pig) {
                        return new CraftPig(server, (Pig)entity);
                    }
                    if (entity instanceof TamableAnimal) {
                        if (entity instanceof Wolf) {
                            return new CraftWolf(server, (Wolf)entity);
                        }
                        if (entity instanceof Cat) {
                            return new CraftCat(server, (Cat)entity);
                        }
                        if (!(entity instanceof Parrot)) throw new AssertionError((Object)("Unknown entity " + (entity == null ? null : entity.getClass())));
                        return new CraftParrot(server, (Parrot)entity);
                    }
                    if (entity instanceof Sheep) {
                        return new CraftSheep(server, (Sheep)entity);
                    }
                    if (entity instanceof AbstractHorse) {
                        if (entity instanceof AbstractChestedHorse) {
                            if (entity instanceof Donkey) {
                                return new CraftDonkey(server, (Donkey)entity);
                            }
                            if (entity instanceof Mule) {
                                return new CraftMule(server, (Mule)entity);
                            }
                            if (entity instanceof TraderLlama) {
                                return new CraftTraderLlama(server, (TraderLlama)entity);
                            }
                            if (!(entity instanceof Llama)) throw new AssertionError((Object)("Unknown entity " + (entity == null ? null : entity.getClass())));
                            return new CraftLlama(server, (Llama)entity);
                        }
                        if (entity instanceof Horse) {
                            return new CraftHorse(server, (Horse)entity);
                        }
                        if (entity instanceof SkeletonHorse) {
                            return new CraftSkeletonHorse(server, (SkeletonHorse)entity);
                        }
                        if (entity instanceof ZombieHorse) {
                            return new CraftZombieHorse(server, (ZombieHorse)entity);
                        }
                        if (!(entity instanceof Camel)) throw new AssertionError((Object)("Unknown entity " + (entity == null ? null : entity.getClass())));
                        return new CraftCamel(server, (Camel)entity);
                    }
                    if (entity instanceof Rabbit) {
                        return new CraftRabbit(server, (Rabbit)entity);
                    }
                    if (entity instanceof PolarBear) {
                        return new CraftPolarBear(server, (PolarBear)entity);
                    }
                    if (entity instanceof Turtle) {
                        return new CraftTurtle(server, (Turtle)entity);
                    }
                    if (entity instanceof Ocelot) {
                        return new CraftOcelot(server, (Ocelot)entity);
                    }
                    if (entity instanceof Panda) {
                        return new CraftPanda(server, (Panda)entity);
                    }
                    if (entity instanceof Fox) {
                        return new CraftFox(server, (Fox)entity);
                    }
                    if (entity instanceof Bee) {
                        return new CraftBee(server, (Bee)entity);
                    }
                    if (entity instanceof Hoglin) {
                        return new CraftHoglin(server, (Hoglin)entity);
                    }
                    if (entity instanceof Strider) {
                        return new CraftStrider(server, (Strider)entity);
                    }
                    if (entity instanceof Axolotl) {
                        return new CraftAxolotl(server, (Axolotl)entity);
                    }
                    if (entity instanceof Goat) {
                        return new CraftGoat(server, (Goat)entity);
                    }
                    if (entity instanceof Frog) {
                        return new CraftFrog(server, (Frog)entity);
                    }
                    if (!(entity instanceof Sniffer)) return new CraftAnimals(server, (Animal)entity);
                    return new CraftSniffer(server, (Sniffer)entity);
                }
                if (entity instanceof Monster) {
                    if (entity instanceof Zombie) {
                        if (entity instanceof ZombifiedPiglin) {
                            return new CraftPigZombie(server, (ZombifiedPiglin)entity);
                        }
                        if (entity instanceof Husk) {
                            return new CraftHusk(server, (Husk)entity);
                        }
                        if (entity instanceof ZombieVillager) {
                            return new CraftVillagerZombie(server, (ZombieVillager)entity);
                        }
                        if (!(entity instanceof Drowned)) return new CraftZombie(server, (Zombie)entity);
                        return new CraftDrowned(server, (Drowned)entity);
                    }
                    if (entity instanceof Creeper) {
                        return new CraftCreeper(server, (Creeper)entity);
                    }
                    if (entity instanceof EnderMan) {
                        return new CraftEnderman(server, (EnderMan)entity);
                    }
                    if (entity instanceof Silverfish) {
                        return new CraftSilverfish(server, (Silverfish)entity);
                    }
                    if (entity instanceof Giant) {
                        return new CraftGiant(server, (Giant)entity);
                    }
                    if (entity instanceof AbstractSkeleton) {
                        if (entity instanceof Stray) {
                            return new CraftStray(server, (Stray)entity);
                        }
                        if (entity instanceof WitherSkeleton) {
                            return new CraftWitherSkeleton(server, (WitherSkeleton)entity);
                        }
                        if (!(entity instanceof Skeleton)) throw new AssertionError((Object)("Unknown entity " + (entity == null ? null : entity.getClass())));
                        return new CraftSkeleton(server, (Skeleton)entity);
                    }
                    if (entity instanceof Blaze) {
                        return new CraftBlaze(server, (Blaze)entity);
                    }
                    if (entity instanceof Witch) {
                        return new CraftWitch(server, (Witch)entity);
                    }
                    if (entity instanceof WitherBoss) {
                        return new CraftWither(server, (WitherBoss)entity);
                    }
                    if (entity instanceof Spider) {
                        if (!(entity instanceof CaveSpider)) return new CraftSpider(server, (Spider)entity);
                        return new CraftCaveSpider(server, (CaveSpider)entity);
                    }
                    if (entity instanceof Endermite) {
                        return new CraftEndermite(server, (Endermite)entity);
                    }
                    if (entity instanceof Guardian) {
                        if (!(entity instanceof ElderGuardian)) return new CraftGuardian(server, (Guardian)entity);
                        return new CraftElderGuardian(server, (ElderGuardian)entity);
                    }
                    if (entity instanceof Vex) {
                        return new CraftVex(server, (Vex)entity);
                    }
                    if (entity instanceof AbstractIllager) {
                        if (entity instanceof SpellcasterIllager) {
                            if (entity instanceof Evoker) {
                                return new CraftEvoker(server, (Evoker)entity);
                            }
                            if (!(entity instanceof Illusioner)) return new CraftSpellcaster(server, (SpellcasterIllager)entity);
                            return new CraftIllusioner(server, (Illusioner)entity);
                        }
                        if (entity instanceof Vindicator) {
                            return new CraftVindicator(server, (Vindicator)entity);
                        }
                        if (!(entity instanceof Pillager)) return new CraftIllager(server, (AbstractIllager)entity);
                        return new CraftPillager(server, (Pillager)entity);
                    }
                    if (entity instanceof Ravager) {
                        return new CraftRavager(server, (Ravager)entity);
                    }
                    if (entity instanceof AbstractPiglin) {
                        if (entity instanceof Piglin) {
                            return new CraftPiglin(server, (Piglin)entity);
                        }
                        if (!(entity instanceof PiglinBrute)) return new CraftPiglinAbstract(server, (AbstractPiglin)entity);
                        return new CraftPiglinBrute(server, (PiglinBrute)entity);
                    }
                    if (entity instanceof Zoglin) {
                        return new CraftZoglin(server, (Zoglin)entity);
                    }
                    if (!(entity instanceof Warden)) return new CraftMonster(server, (Monster)entity);
                    return new CraftWarden(server, (Warden)entity);
                }
                if (entity instanceof AbstractGolem) {
                    if (entity instanceof SnowGolem) {
                        return new CraftSnowman(server, (SnowGolem)entity);
                    }
                    if (entity instanceof IronGolem) {
                        return new CraftIronGolem(server, (IronGolem)entity);
                    }
                    if (!(entity instanceof Shulker)) throw new AssertionError((Object)("Unknown entity " + (entity == null ? null : entity.getClass())));
                    return new CraftShulker(server, (Shulker)entity);
                }
                if (entity instanceof AbstractVillager) {
                    if (entity instanceof Villager) {
                        return new CraftVillager(server, (Villager)entity);
                    }
                    if (!(entity instanceof WanderingTrader)) return new CraftAbstractVillager(server, (AbstractVillager)entity);
                    return new CraftWanderingTrader(server, (WanderingTrader)entity);
                }
                if (!(entity instanceof Allay)) return new CraftCreature(server, (PathfinderMob)entity);
                return new CraftAllay(server, (Allay)entity);
            }
            if (entity instanceof Slime) {
                if (!(entity instanceof MagmaCube)) return new CraftSlime(server, (Slime)entity);
                return new CraftMagmaCube(server, (MagmaCube)entity);
            }
            if (entity instanceof FlyingMob) {
                if (entity instanceof Ghast) {
                    return new CraftGhast(server, (Ghast)entity);
                }
                if (!(entity instanceof Phantom)) return new CraftFlying(server, (FlyingMob)entity);
                return new CraftPhantom(server, (Phantom)entity);
            }
            if (entity instanceof EnderDragon) {
                return new CraftEnderDragon(server, (EnderDragon)entity);
            }
            if (entity instanceof AmbientCreature) {
                if (!(entity instanceof Bat)) return new CraftAmbient(server, (AmbientCreature)entity);
                return new CraftBat(server, (Bat)entity);
            }
            if (!(entity instanceof ArmorStand)) return new CraftLivingEntity(server, (LivingEntity)entity);
            return new CraftArmorStand(server, (ArmorStand)entity);
        }
        if (entity instanceof EnderDragonPart) {
            EnderDragonPart part = (EnderDragonPart)entity;
            if (!(part.f_31010_ instanceof EnderDragon)) return new CraftComplexPart(server, (EnderDragonPart)entity);
            return new CraftEnderDragonPart(server, (EnderDragonPart)entity);
        }
        if (entity instanceof ExperienceOrb) {
            return new CraftExperienceOrb(server, (ExperienceOrb)entity);
        }
        if (entity instanceof Arrow) {
            return new CraftTippedArrow(server, (Arrow)entity);
        }
        if (entity instanceof SpectralArrow) {
            return new CraftSpectralArrow(server, (SpectralArrow)entity);
        }
        if (entity instanceof AbstractArrow) {
            if (!(entity instanceof ThrownTrident)) return new CraftArrow(server, (AbstractArrow)entity);
            return new CraftTrident(server, (ThrownTrident)entity);
        }
        if (entity instanceof Boat) {
            if (!(entity instanceof ChestBoat)) return new CraftBoat(server, (Boat)entity);
            return new CraftChestBoat(server, (ChestBoat)entity);
        }
        if (entity instanceof ThrowableProjectile) {
            if (entity instanceof ThrownEgg) {
                return new CraftEgg(server, (ThrownEgg)entity);
            }
            if (entity instanceof Snowball) {
                return new CraftSnowball(server, (Snowball)entity);
            }
            if (entity instanceof ThrownPotion) {
                return new CraftThrownPotion(server, (ThrownPotion)entity);
            }
            if (entity instanceof ThrownEnderpearl) {
                return new CraftEnderPearl(server, (ThrownEnderpearl)entity);
            }
            if (!(entity instanceof ThrownExperienceBottle)) throw new AssertionError((Object)("Unknown entity " + (entity == null ? null : entity.getClass())));
            return new CraftThrownExpBottle(server, (ThrownExperienceBottle)entity);
        }
        if (entity instanceof FallingBlockEntity) {
            return new CraftFallingBlock(server, (FallingBlockEntity)entity);
        }
        if (entity instanceof AbstractHurtingProjectile) {
            if (entity instanceof SmallFireball) {
                return new CraftSmallFireball(server, (SmallFireball)entity);
            }
            if (entity instanceof LargeFireball) {
                return new CraftLargeFireball(server, (LargeFireball)entity);
            }
            if (entity instanceof WitherSkull) {
                return new CraftWitherSkull(server, (WitherSkull)entity);
            }
            if (!(entity instanceof DragonFireball)) return new CraftFireball(server, (AbstractHurtingProjectile)entity);
            return new CraftDragonFireball(server, (DragonFireball)entity);
        }
        if (entity instanceof EyeOfEnder) {
            return new CraftEnderSignal(server, (EyeOfEnder)entity);
        }
        if (entity instanceof EndCrystal) {
            return new CraftEnderCrystal(server, (EndCrystal)entity);
        }
        if (entity instanceof FishingHook) {
            return new CraftFishHook(server, (FishingHook)entity);
        }
        if (entity instanceof ItemEntity) {
            return new CraftItem(server, (ItemEntity)entity);
        }
        if (entity instanceof LightningBolt) {
            return new CraftLightningStrike(server, (LightningBolt)entity);
        }
        if (entity instanceof AbstractMinecart) {
            if (entity instanceof MinecartFurnace) {
                return new CraftMinecartFurnace(server, (MinecartFurnace)entity);
            }
            if (entity instanceof MinecartChest) {
                return new CraftMinecartChest(server, (MinecartChest)entity);
            }
            if (entity instanceof MinecartTNT) {
                return new CraftMinecartTNT(server, (MinecartTNT)entity);
            }
            if (entity instanceof MinecartHopper) {
                return new CraftMinecartHopper(server, (MinecartHopper)entity);
            }
            if (entity instanceof MinecartSpawner) {
                return new CraftMinecartMobSpawner(server, (MinecartSpawner)entity);
            }
            if (entity instanceof Minecart) {
                return new CraftMinecartRideable(server, (AbstractMinecart)((Minecart)entity));
            }
            if (!(entity instanceof MinecartCommandBlock)) throw new AssertionError((Object)("Unknown entity " + (entity == null ? null : entity.getClass())));
            return new CraftMinecartCommand(server, (MinecartCommandBlock)entity);
        }
        if (entity instanceof HangingEntity) {
            if (entity instanceof Painting) {
                return new CraftPainting(server, (Painting)entity);
            }
            if (entity instanceof ItemFrame) {
                if (!(entity instanceof GlowItemFrame)) return new CraftItemFrame(server, (ItemFrame)entity);
                return new CraftGlowItemFrame(server, (GlowItemFrame)entity);
            }
            if (!(entity instanceof LeashFenceKnotEntity)) return new CraftHanging(server, (HangingEntity)entity);
            return new CraftLeash(server, (LeashFenceKnotEntity)entity);
        }
        if (entity instanceof PrimedTnt) {
            return new CraftTNTPrimed(server, (PrimedTnt)entity);
        }
        if (entity instanceof FireworkRocketEntity) {
            return new CraftFirework(server, (FireworkRocketEntity)entity);
        }
        if (entity instanceof ShulkerBullet) {
            return new CraftShulkerBullet(server, (ShulkerBullet)entity);
        }
        if (entity instanceof AreaEffectCloud) {
            return new CraftAreaEffectCloud(server, (AreaEffectCloud)entity);
        }
        if (entity instanceof EvokerFangs) {
            return new CraftEvokerFangs(server, (EvokerFangs)entity);
        }
        if (entity instanceof LlamaSpit) {
            return new CraftLlamaSpit(server, (LlamaSpit)entity);
        }
        if (entity instanceof Marker) {
            return new CraftMarker(server, (Marker)entity);
        }
        if (entity instanceof Interaction) {
            return new CraftInteraction(server, (Interaction)entity);
        }
        if (!(entity instanceof Display)) throw new AssertionError((Object)("Unknown entity " + (entity == null ? null : entity.getClass())));
        if (entity instanceof Display.BlockDisplay) {
            return new CraftBlockDisplay(server, (Display.BlockDisplay)entity);
        }
        if (entity instanceof Display.ItemDisplay) {
            return new CraftItemDisplay(server, (Display.ItemDisplay)entity);
        }
        if (!(entity instanceof Display.TextDisplay)) return new CraftDisplay(server, (Display)entity);
        return new CraftTextDisplay(server, (Display.TextDisplay)entity);
    }

    @Override
    public Location getLocation() {
        return CraftLocation.toBukkit(this.entity.m_20182_(), this.getWorld(), this.entity.getBukkitYaw(), this.entity.m_146909_());
    }

    @Override
    public Location getLocation(Location loc) {
        if (loc != null) {
            loc.setWorld(this.getWorld());
            loc.setX(this.entity.m_20185_());
            loc.setY(this.entity.m_20186_());
            loc.setZ(this.entity.m_20189_());
            loc.setYaw(this.entity.getBukkitYaw());
            loc.setPitch(this.entity.m_146909_());
        }
        return loc;
    }

    @Override
    public Vector getVelocity() {
        return CraftVector.toBukkit(this.entity.m_20184_());
    }

    @Override
    public void setVelocity(Vector velocity) {
        Preconditions.checkArgument((velocity != null ? 1 : 0) != 0, (Object)"velocity");
        velocity.checkFinite();
        this.entity.m_20256_(CraftVector.toNMS(velocity));
        this.entity.f_19864_ = true;
    }

    @Override
    public double getHeight() {
        return this.getHandle().m_20206_();
    }

    @Override
    public double getWidth() {
        return this.getHandle().m_20205_();
    }

    @Override
    public BoundingBox getBoundingBox() {
        AABB bb = this.getHandle().m_20191_();
        return new BoundingBox(bb.f_82288_, bb.f_82289_, bb.f_82290_, bb.f_82291_, bb.f_82292_, bb.f_82293_);
    }

    @Override
    public boolean isOnGround() {
        if (this.entity instanceof AbstractArrow) {
            return ((AbstractArrow)this.entity).f_36703_;
        }
        return this.entity.m_20096_();
    }

    @Override
    public boolean isInWater() {
        return this.entity.m_20069_();
    }

    @Override
    public World getWorld() {
        return this.entity.m_9236_().getWorld();
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        NumberConversions.checkFinite(pitch, "pitch not finite");
        NumberConversions.checkFinite(yaw, "yaw not finite");
        yaw = Location.normalizeYaw(yaw);
        pitch = Location.normalizePitch(pitch);
        this.entity.m_146922_(yaw);
        this.entity.m_146926_(pitch);
        this.entity.f_19859_ = yaw;
        this.entity.f_19860_ = pitch;
        this.entity.m_5616_(yaw);
    }

    @Override
    public boolean teleport(Location location) {
        return this.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"location cannot be null");
        location.checkFinite();
        if (this.entity.m_20160_() || this.entity.m_213877_()) {
            return false;
        }
        this.entity.m_8127_();
        if (location.getWorld() != null && !location.getWorld().equals(this.getWorld())) {
            Preconditions.checkState((!this.entity.generation ? 1 : 0) != 0, (Object)"Cannot teleport entity to an other world during world generation");
            this.entity.teleportTo(((CraftWorld)location.getWorld()).getHandle(), CraftLocation.toPosition(location));
            return true;
        }
        this.entity.m_19890_(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        this.entity.m_5616_(location.getYaw());
        return true;
    }

    @Override
    public boolean teleport(Entity destination) {
        return this.teleport(destination.getLocation());
    }

    @Override
    public boolean teleport(Entity destination, PlayerTeleportEvent.TeleportCause cause) {
        return this.teleport(destination.getLocation(), cause);
    }

    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        Preconditions.checkState((!this.entity.generation ? 1 : 0) != 0, (Object)"Cannot get nearby entities during world generation");
        AsyncCatcher.catchOp("getNearbyEntities");
        List notchEntityList = this.entity.m_9236_().m_6249_(this.entity, this.entity.m_20191_().m_82377_(x, y, z), (Predicate)Predicates.alwaysTrue());
        ArrayList<Entity> bukkitEntityList = new ArrayList<Entity>(notchEntityList.size());
        for (net.minecraft.world.entity.Entity e : notchEntityList) {
            bukkitEntityList.add(e.getBukkitEntity());
        }
        return bukkitEntityList;
    }

    @Override
    public int getEntityId() {
        return this.entity.m_19879_();
    }

    @Override
    public int getFireTicks() {
        return this.entity.m_20094_();
    }

    @Override
    public int getMaxFireTicks() {
        return this.entity.m_6101_();
    }

    @Override
    public void setFireTicks(int ticks) {
        this.entity.m_7311_(ticks);
    }

    @Override
    public void setVisualFire(boolean fire) {
        this.getHandle().f_146813_ = fire;
    }

    @Override
    public boolean isVisualFire() {
        return this.getHandle().f_146813_;
    }

    @Override
    public int getFreezeTicks() {
        return this.getHandle().m_146888_();
    }

    @Override
    public int getMaxFreezeTicks() {
        return this.getHandle().m_146891_();
    }

    @Override
    public void setFreezeTicks(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (String)"Ticks (%s) cannot be less than 0", (int)ticks);
        this.getHandle().m_146917_(ticks);
    }

    @Override
    public boolean isFrozen() {
        return this.getHandle().m_146890_();
    }

    @Override
    public void remove() {
        this.entity.m_146870_();
    }

    @Override
    public boolean isDead() {
        return !this.entity.m_6084_();
    }

    @Override
    public boolean isValid() {
        return this.entity.m_6084_() && this.entity.valid && this.entity.isChunkLoaded();
    }

    @Override
    public Server getServer() {
        return this.server;
    }

    @Override
    public boolean isPersistent() {
        return this.entity.persist;
    }

    @Override
    public void setPersistent(boolean persistent) {
        this.entity.persist = persistent;
    }

    public Vector getMomentum() {
        return this.getVelocity();
    }

    public void setMomentum(Vector value) {
        this.setVelocity(value);
    }

    @Override
    public Entity getPassenger() {
        return this.isEmpty() ? null : ((net.minecraft.world.entity.Entity)this.getHandle().f_19823_.get(0)).getBukkitEntity();
    }

    @Override
    public boolean setPassenger(Entity passenger) {
        Preconditions.checkArgument((!this.equals(passenger) ? 1 : 0) != 0, (Object)"Entity cannot ride itself.");
        if (passenger instanceof CraftEntity) {
            this.eject();
            return ((CraftEntity)passenger).getHandle().m_20329_(this.getHandle());
        }
        return false;
    }

    @Override
    public List<Entity> getPassengers() {
        return Lists.newArrayList((Iterable)Lists.transform((List)this.getHandle().f_19823_, input -> input.getBukkitEntity()));
    }

    @Override
    public boolean addPassenger(Entity passenger) {
        Preconditions.checkArgument((passenger != null ? 1 : 0) != 0, (Object)"Entity passenger cannot be null");
        Preconditions.checkArgument((!this.equals(passenger) ? 1 : 0) != 0, (Object)"Entity cannot ride itself.");
        return ((CraftEntity)passenger).getHandle().m_7998_(this.getHandle(), true);
    }

    @Override
    public boolean removePassenger(Entity passenger) {
        Preconditions.checkArgument((passenger != null ? 1 : 0) != 0, (Object)"Entity passenger cannot be null");
        ((CraftEntity)passenger).getHandle().m_8127_();
        return true;
    }

    @Override
    public boolean isEmpty() {
        return !this.getHandle().m_20160_();
    }

    @Override
    public boolean eject() {
        if (this.isEmpty()) {
            return false;
        }
        this.getHandle().m_20153_();
        return true;
    }

    @Override
    public float getFallDistance() {
        return this.getHandle().f_19789_;
    }

    @Override
    public void setFallDistance(float distance) {
        this.getHandle().f_19789_ = distance;
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event) {
        this.lastDamageEvent = event;
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return this.lastDamageEvent;
    }

    @Override
    public UUID getUniqueId() {
        return this.getHandle().m_20148_();
    }

    @Override
    public int getTicksLived() {
        return this.getHandle().f_19797_;
    }

    @Override
    public void setTicksLived(int value) {
        Preconditions.checkArgument((value > 0 ? 1 : 0) != 0, (String)"Age value (%s) must be greater than 0", (int)value);
        this.getHandle().f_19797_ = value;
    }

    public net.minecraft.world.entity.Entity getHandle() {
        return this.entity;
    }

    @Override
    public final org.bukkit.entity.EntityType getType() {
        return this.entityType;
    }

    @Override
    public void playEffect(EntityEffect type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"type");
        Preconditions.checkState((!this.entity.generation ? 1 : 0) != 0, (Object)"Cannot play effect during world generation");
        if (type.getApplicable().isInstance(this)) {
            this.getHandle().m_9236_().m_7605_(this.getHandle(), type.getData());
        }
    }

    @Override
    public Sound getSwimSound() {
        return CraftSound.getBukkit(this.getHandle().getSwimSound0());
    }

    @Override
    public Sound getSwimSplashSound() {
        return CraftSound.getBukkit(this.getHandle().getSwimSplashSound0());
    }

    @Override
    public Sound getSwimHighSpeedSplashSound() {
        return CraftSound.getBukkit(this.getHandle().getSwimHighSpeedSplashSound0());
    }

    public void setHandle(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    public String toString() {
        return "CraftEntity{id=" + this.getEntityId() + '}';
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CraftEntity other = (CraftEntity)obj;
        return this.getEntityId() == other.getEntityId();
    }

    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.getEntityId();
        return hash;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.server.getEntityMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return this.server.getEntityMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return this.server.getEntityMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.server.getEntityMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public boolean isInsideVehicle() {
        return this.getHandle().m_20159_();
    }

    @Override
    public boolean leaveVehicle() {
        if (!this.isInsideVehicle()) {
            return false;
        }
        this.getHandle().m_8127_();
        return true;
    }

    @Override
    public Entity getVehicle() {
        if (!this.isInsideVehicle()) {
            return null;
        }
        return this.getHandle().m_20202_().getBukkitEntity();
    }

    @Override
    public void setCustomName(String name) {
        if (name != null && name.length() > 256) {
            name = name.substring(0, 256);
        }
        this.getHandle().m_6593_(CraftChatMessage.fromStringOrNull(name));
    }

    @Override
    public String getCustomName() {
        Component name = this.getHandle().m_7770_();
        if (name == null) {
            return null;
        }
        return CraftChatMessage.fromComponent(name);
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        this.getHandle().m_20340_(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.getHandle().m_20151_();
    }

    @Override
    public void setVisibleByDefault(boolean visible) {
        if (this.getHandle().visibleByDefault != visible) {
            if (visible) {
                for (org.bukkit.entity.Player player : this.server.getOnlinePlayers()) {
                    ((CraftPlayer)player).resetAndShowEntity(this);
                }
            } else {
                for (org.bukkit.entity.Player player : this.server.getOnlinePlayers()) {
                    ((CraftPlayer)player).resetAndHideEntity(this);
                }
            }
            this.getHandle().visibleByDefault = visible;
        }
    }

    @Override
    public boolean isVisibleByDefault() {
        return this.getHandle().visibleByDefault;
    }

    @Override
    public void sendMessage(String message) {
    }

    @Override
    public void sendMessage(String ... messages) {
    }

    @Override
    public void sendMessage(UUID sender, String message) {
        this.sendMessage(message);
    }

    @Override
    public void sendMessage(UUID sender, String ... messages) {
        this.sendMessage(messages);
    }

    @Override
    public String getName() {
        return CraftChatMessage.fromComponent(this.getHandle().m_7755_());
    }

    @Override
    public boolean isPermissionSet(String name) {
        return CraftEntity.getPermissibleBase().isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return CraftEntity.getPermissibleBase().isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String name) {
        return CraftEntity.getPermissibleBase().hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return CraftEntity.getPermissibleBase().hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return CraftEntity.getPermissibleBase().addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return CraftEntity.getPermissibleBase().addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return CraftEntity.getPermissibleBase().addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return CraftEntity.getPermissibleBase().addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        CraftEntity.getPermissibleBase().removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        CraftEntity.getPermissibleBase().recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return CraftEntity.getPermissibleBase().getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return CraftEntity.getPermissibleBase().isOp();
    }

    @Override
    public void setOp(boolean value) {
        CraftEntity.getPermissibleBase().setOp(value);
    }

    @Override
    public void setGlowing(boolean flag) {
        this.getHandle().m_146915_(flag);
    }

    @Override
    public boolean isGlowing() {
        return this.getHandle().m_142038_();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        this.getHandle().m_20331_(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return this.getHandle().m_6673_(this.getHandle().m_269291_().m_269264_());
    }

    @Override
    public boolean isSilent() {
        return this.getHandle().m_20067_();
    }

    @Override
    public void setSilent(boolean flag) {
        this.getHandle().m_20225_(flag);
    }

    @Override
    public boolean hasGravity() {
        return !this.getHandle().m_20068_();
    }

    @Override
    public void setGravity(boolean gravity) {
        this.getHandle().m_20242_(!gravity);
    }

    @Override
    public int getPortalCooldown() {
        return this.getHandle().f_19839_;
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        this.getHandle().f_19839_ = cooldown;
    }

    @Override
    public Set<String> getScoreboardTags() {
        return this.getHandle().m_19880_();
    }

    @Override
    public boolean addScoreboardTag(String tag) {
        return this.getHandle().m_20049_(tag);
    }

    @Override
    public boolean removeScoreboardTag(String tag) {
        return this.getHandle().m_20137_(tag);
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return PistonMoveReaction.getById(this.getHandle().m_7752_().ordinal());
    }

    @Override
    public BlockFace getFacing() {
        return CraftBlock.notchToBlockFace(this.getHandle().m_6374_());
    }

    @Override
    public CraftPersistentDataContainer getPersistentDataContainer() {
        return this.persistentDataContainer;
    }

    @Override
    public Pose getPose() {
        return Pose.values()[this.getHandle().m_20089_().ordinal()];
    }

    @Override
    public SpawnCategory getSpawnCategory() {
        return CraftSpawnCategory.toBukkit(this.getHandle().m_6095_().m_20674_());
    }

    public void storeBukkitValues(CompoundTag c) {
        if (!this.persistentDataContainer.isEmpty()) {
            c.m_128365_("BukkitValues", (Tag)this.persistentDataContainer.toTagCompound());
        }
    }

    public void readBukkitValues(CompoundTag c) {
        Tag base = c.m_128423_("BukkitValues");
        if (base instanceof CompoundTag) {
            this.persistentDataContainer.putAll((CompoundTag)base);
        }
    }

    protected CompoundTag save() {
        CompoundTag nbttagcompound = new CompoundTag();
        nbttagcompound.m_128359_("id", this.getHandle().m_20078_());
        this.getHandle().m_20240_(nbttagcompound);
        return nbttagcompound;
    }

    protected void update() {
        if (!this.getHandle().m_6084_()) {
            return;
        }
        ServerLevel world = ((CraftWorld)this.getWorld()).getHandle();
        ChunkMap.TrackedEntity entityTracker = (ChunkMap.TrackedEntity)world.m_7726_().f_8325_.f_140150_.get(this.getEntityId());
        if (entityTracker == null) {
            return;
        }
        entityTracker.m_140489_(this.getHandle().m_5654_());
    }

    private static PermissibleBase getPermissibleBase() {
        if (perm == null) {
            perm = new PermissibleBase(new ServerOperator(){

                @Override
                public boolean isOp() {
                    return false;
                }

                @Override
                public void setOp(boolean value) {
                }
            });
        }
        return perm;
    }

    @Override
    public Entity.Spigot spigot() {
        return this.spigot;
    }
}

