/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.Unsafe
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.AgeableMob
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
 *  net.minecraft.world.entity.Mob
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
 *  net.minecraft.world.entity.projectile.Fireball
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.entity.projectile.FishingHook
 *  net.minecraft.world.entity.projectile.LargeFireball
 *  net.minecraft.world.entity.projectile.LlamaSpit
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.ShulkerBullet
 *  net.minecraft.world.entity.projectile.SmallFireball
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.entity.projectile.SpectralArrow
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 *  net.minecraft.world.entity.projectile.ThrownEgg
 *  net.minecraft.world.entity.projectile.ThrownEnderpearl
 *  net.minecraft.world.entity.projectile.ThrownExperienceBottle
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.entity.projectile.ThrownTrident
 *  net.minecraft.world.entity.projectile.WitherSkull
 *  net.minecraft.world.entity.raid.Raider
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.AbstractMinecartContainer
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.entity.vehicle.ChestBoat
 *  net.minecraft.world.entity.vehicle.Minecart
 *  net.minecraft.world.entity.vehicle.MinecartChest
 *  net.minecraft.world.entity.vehicle.MinecartCommandBlock
 *  net.minecraft.world.entity.vehicle.MinecartFurnace
 *  net.minecraft.world.entity.vehicle.MinecartHopper
 *  net.minecraft.world.entity.vehicle.MinecartSpawner
 *  net.minecraft.world.entity.vehicle.MinecartTNT
 */
package io.izzel.arclight.common.mod.server.entity;

import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.server.entity.ArclightModAbstractSkeleton;
import io.izzel.arclight.common.mod.server.entity.ArclightModChestedHorse;
import io.izzel.arclight.common.mod.server.entity.ArclightModEntity;
import io.izzel.arclight.common.mod.server.entity.ArclightModHorse;
import io.izzel.arclight.common.mod.server.entity.ArclightModMinecart;
import io.izzel.arclight.common.mod.server.entity.ArclightModMinecartContainer;
import io.izzel.arclight.common.mod.server.entity.ArclightModMob;
import io.izzel.arclight.common.mod.server.entity.ArclightModProjectile;
import io.izzel.arclight.common.mod.server.entity.ArclightModRaider;
import io.izzel.arclight.common.mod.server.entity.ArclightModThrowableProjectile;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.entity.vehicle.MinecartHopper;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractVillager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAgeable;
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
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
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
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGolem;
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
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartRideable;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartTNT;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMob;
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
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSizedFireball;
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
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTameableAnimal;
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
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.Animals;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Cod;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EnderSignal;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Fish;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.GlowSquid;
import org.bukkit.entity.Goat;
import org.bukkit.entity.Golem;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Illager;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LeashHitch;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Monster;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.PiglinAbstract;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.SizedFireball;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Sniffer;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spellcaster;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Steerable;
import org.bukkit.entity.Strider;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Trident;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Vehicle;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.WaterMob;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zoglin;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.entity.minecart.StorageMinecart;

public class EntityClassLookup {
    private static final Map<Class<?>, EntityClass<?>> nmsClassMap = new ConcurrentHashMap();
    private static final Map<Class<?>, EntityClass<?>> NMS_TO_BUKKIT = new HashMap();

    public static void init() {
        HashSet<Class> allEntityClasses = new HashSet<Class>();
        for (EntityType bukkitType : EntityType.values()) {
            Class<? extends Entity> entityClass = bukkitType.getEntityClass();
            if (entityClass == null || allEntityClasses.contains(entityClass)) continue;
            LinkedList next = new LinkedList();
            next.add(entityClass);
            while (!next.isEmpty()) {
                Class cl = (Class)next.pollFirst();
                if (allEntityClasses.contains(cl)) continue;
                allEntityClasses.add(cl);
                for (Class<?> intf : cl.getInterfaces()) {
                    if (!Entity.class.isAssignableFrom(intf)) continue;
                    next.addLast(intf);
                }
            }
        }
        Set<Class<Vehicle>> ignored = Set.of(Explosive.class, Damageable.class, NPC.class, Boss.class, Breedable.class, Steerable.class, Enemy.class, ComplexLivingEntity.class, Vehicle.class);
        boolean error = false;
        for (Class entityClass : allEntityClasses) {
            Optional<EntityClass> optional;
            if (ignored.contains(entityClass) || !(optional = NMS_TO_BUKKIT.values().stream().filter(c -> c.bukkitClass == entityClass).findAny()).isEmpty()) continue;
            error = true;
            ArclightMod.LOGGER.error(String.valueOf(entityClass) + " has no valid entity class mapping");
        }
        if (error) {
            throw new RuntimeException("Missing valid entity class mapping");
        }
    }

    public static <T extends net.minecraft.world.entity.Entity> BiFunction<CraftServer, T, Entity> getConvert(T entity) {
        return EntityClassLookup.nmsClassMap.computeIfAbsent(entity.getClass(), (Function<Class, EntityClass>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$getConvert$1(net.minecraft.world.entity.Entity java.lang.Class ), (Ljava/lang/Class;)Lio/izzel/arclight/common/mod/server/entity/EntityClassLookup$EntityClass;)(entity)).convert;
    }

    private static <T extends net.minecraft.world.entity.Entity> EntityClass<T> getEntityTypeData(Class<?> type, net.minecraft.world.entity.EntityType<T> entityType) {
        EntityClass<?> entityClass = null;
        Class<?> c = type;
        while (entityClass == null) {
            entityClass = NMS_TO_BUKKIT.get(c);
            c = c.getSuperclass();
        }
        return Objects.requireNonNull(entityClass, "entityClass");
    }

    private static <U extends V, V extends net.minecraft.world.entity.Entity> void add(Class<? super U> cl, EntityClass<? super V> entityClass) {
        if (NMS_TO_BUKKIT.put(cl, entityClass) != null) {
            throw new IllegalStateException("Duplicate " + String.valueOf(cl) + " mapping");
        }
    }

    private static Class<? extends CraftEntity> forName(String name) {
        try {
            return Class.forName(CraftEntity.class.getPackageName() + "." + name).asSubclass(CraftEntity.class);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T extends net.minecraft.world.entity.Entity> BiFunction<CraftServer, T, Entity> convert(String name) {
        try {
            Class<? extends CraftEntity> cl = EntityClassLookup.forName(name);
            for (Constructor<?> constructor : cl.getDeclaredConstructors()) {
                Class<?>[] pTypes;
                if (constructor.getParameterCount() != 2 || !(pTypes = constructor.getParameterTypes())[0].equals(CraftServer.class) || !net.minecraft.world.entity.Entity.class.isAssignableFrom(pTypes[1])) continue;
                constructor.setAccessible(true);
                MethodHandles.Lookup lookup = Unsafe.lookup().in(constructor.getDeclaringClass());
                return LambdaMetafactory.metafactory(lookup, "apply", MethodType.methodType(BiFunction.class), MethodType.methodType(Object.class, Object.class, Object.class), lookup.unreflectConstructor(constructor), lookup.unreflectConstructor(constructor).type()).dynamicInvoker().invoke();
            }
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("convert");
    }

    private static /* synthetic */ EntityClass lambda$getConvert$1(net.minecraft.world.entity.Entity entity, Class k) {
        return EntityClassLookup.getEntityTypeData(k, entity.m_6095_());
    }

    static {
        EntityClassLookup.add(net.minecraft.world.entity.Entity.class, new EntityClass<net.minecraft.world.entity.Entity>(Entity.class, ArclightModEntity.class, ArclightModEntity::new));
        EntityClassLookup.add(AbstractSkeleton.class, new EntityClass<AbstractSkeleton>(org.bukkit.entity.AbstractSkeleton.class, ArclightModAbstractSkeleton.class, ArclightModAbstractSkeleton::new));
        EntityClassLookup.add(Mob.class, new EntityClass<Mob>(org.bukkit.entity.Mob.class, CraftMob.class, ArclightModMob::new));
        EntityClassLookup.add(AbstractMinecart.class, new EntityClass<AbstractMinecart>(org.bukkit.entity.Minecart.class, ArclightModMinecart.class, ArclightModMinecart::new));
        EntityClassLookup.add(AbstractMinecartContainer.class, new EntityClass<AbstractMinecartContainer>(org.bukkit.entity.Minecart.class, ArclightModMinecartContainer.class, ArclightModMinecartContainer::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.horse.AbstractHorse.class, new EntityClass<net.minecraft.world.entity.animal.horse.AbstractHorse>(AbstractHorse.class, ArclightModHorse.class, ArclightModHorse::new));
        EntityClassLookup.add(AbstractChestedHorse.class, new EntityClass<AbstractChestedHorse>(ChestedHorse.class, ArclightModChestedHorse.class, ArclightModChestedHorse::new));
        EntityClassLookup.add(Projectile.class, new EntityClass<Projectile>(org.bukkit.entity.Projectile.class, ArclightModProjectile.class, ArclightModProjectile::new));
        EntityClassLookup.add(Raider.class, new EntityClass<Raider>(org.bukkit.entity.Raider.class, ArclightModRaider.class, ArclightModRaider::new));
        EntityClassLookup.add(LivingEntity.class, new EntityClass<LivingEntity>(org.bukkit.entity.LivingEntity.class, CraftLivingEntity.class, CraftLivingEntity::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Monster.class, new EntityClass<net.minecraft.world.entity.monster.Monster>(Monster.class, CraftMonster.class, CraftMonster::new));
        EntityClassLookup.add(PathfinderMob.class, new EntityClass<PathfinderMob>(Creature.class, CraftCreature.class, CraftCreature::new));
        EntityClassLookup.add(AgeableMob.class, new EntityClass<AgeableMob>(Ageable.class, CraftAgeable.class, CraftAgeable::new));
        EntityClassLookup.add(net.minecraft.world.entity.npc.AbstractVillager.class, new EntityClass<net.minecraft.world.entity.npc.AbstractVillager>(AbstractVillager.class, CraftAbstractVillager.class, CraftAbstractVillager::new));
        EntityClassLookup.add(net.minecraft.world.entity.projectile.AbstractArrow.class, new EntityClass<net.minecraft.world.entity.projectile.AbstractArrow>(AbstractArrow.class, CraftArrow.class, CraftArrow::new));
        EntityClassLookup.add(Animal.class, new EntityClass<Animal>(Animals.class, CraftAnimals.class, CraftAnimals::new));
        EntityClassLookup.add(Fireball.class, new EntityClass<Fireball>(SizedFireball.class, CraftSizedFireball.class, CraftSizedFireball::new));
        EntityClassLookup.add(AbstractHurtingProjectile.class, new EntityClass<AbstractHurtingProjectile>(org.bukkit.entity.Fireball.class, CraftFireball.class, CraftFireball::new));
        EntityClassLookup.add(Display.class, new EntityClass<Display>(org.bukkit.entity.Display.class, CraftDisplay.class, CraftDisplay::new));
        EntityClassLookup.add(AbstractIllager.class, new EntityClass<AbstractIllager>(Illager.class, CraftIllager.class, CraftIllager::new));
        EntityClassLookup.add(ThrowableItemProjectile.class, new EntityClass<ThrowableItemProjectile>(ThrowableProjectile.class, ArclightModThrowableProjectile.class, ArclightModThrowableProjectile::new));
        EntityClassLookup.add(HangingEntity.class, new EntityClass<HangingEntity>(Hanging.class, CraftHanging.class, CraftHanging::new));
        EntityClassLookup.add(SpellcasterIllager.class, new EntityClass<SpellcasterIllager>(Spellcaster.class, CraftSpellcaster.class, CraftSpellcaster::new));
        EntityClassLookup.add(AmbientCreature.class, new EntityClass<AmbientCreature>(Ambient.class, CraftAmbient.class, CraftAmbient::new));
        EntityClassLookup.add(TamableAnimal.class, new EntityClass<TamableAnimal>(Tameable.class, CraftTameableAnimal.class, CraftTameableAnimal::new));
        EntityClassLookup.add(AbstractPiglin.class, new EntityClass<AbstractPiglin>(PiglinAbstract.class, CraftPiglinAbstract.class, CraftPiglinAbstract::new));
        EntityClassLookup.add(FlyingMob.class, new EntityClass<FlyingMob>(Flying.class, CraftFlying.class, CraftFlying::new));
        EntityClassLookup.add(WaterAnimal.class, new EntityClass<WaterAnimal>(WaterMob.class, CraftWaterMob.class, CraftWaterMob::new));
        EntityClassLookup.add(AbstractGolem.class, new EntityClass<AbstractGolem>(Golem.class, CraftGolem.class, CraftGolem::new));
        EntityClassLookup.add(Player.class, new EntityClass<Player>(HumanEntity.class, CraftHumanEntity.class, CraftHumanEntity::new));
        EntityClassLookup.add(AbstractFish.class, new EntityClass<AbstractFish>(Fish.class, CraftFish.class, CraftFish::new));
        EntityClassLookup.add(EnderDragonPart.class, new EntityClass<EnderDragonPart>(org.bukkit.entity.EnderDragonPart.class, CraftEnderDragonPart.class, CraftEnderDragonPart::new));
        EntityClassLookup.add(ElderGuardian.class, new EntityClass<ElderGuardian>(org.bukkit.entity.ElderGuardian.class, CraftElderGuardian.class, CraftElderGuardian::new));
        EntityClassLookup.add(WitherSkeleton.class, new EntityClass<WitherSkeleton>(org.bukkit.entity.WitherSkeleton.class, CraftWitherSkeleton.class, CraftWitherSkeleton::new));
        EntityClassLookup.add(Stray.class, new EntityClass<Stray>(org.bukkit.entity.Stray.class, CraftStray.class, CraftStray::new));
        EntityClassLookup.add(Husk.class, new EntityClass<Husk>(org.bukkit.entity.Husk.class, CraftHusk.class, CraftHusk::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.ZombieVillager.class, new EntityClass<net.minecraft.world.entity.monster.ZombieVillager>(ZombieVillager.class, CraftVillagerZombie.class, CraftVillagerZombie::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.horse.SkeletonHorse.class, new EntityClass<net.minecraft.world.entity.animal.horse.SkeletonHorse>(SkeletonHorse.class, CraftSkeletonHorse.class, CraftSkeletonHorse::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.horse.ZombieHorse.class, new EntityClass<net.minecraft.world.entity.animal.horse.ZombieHorse>(ZombieHorse.class, CraftZombieHorse.class, CraftZombieHorse::new));
        EntityClassLookup.add(net.minecraft.world.entity.decoration.ArmorStand.class, new EntityClass<net.minecraft.world.entity.decoration.ArmorStand>(ArmorStand.class, CraftArmorStand.class, CraftArmorStand::new));
        EntityClassLookup.add(Donkey.class, new EntityClass<Donkey>(org.bukkit.entity.Donkey.class, CraftDonkey.class, CraftDonkey::new));
        EntityClassLookup.add(Mule.class, new EntityClass<Mule>(org.bukkit.entity.Mule.class, CraftMule.class, CraftMule::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Evoker.class, new EntityClass<net.minecraft.world.entity.monster.Evoker>(Evoker.class, CraftEvoker.class, CraftEvoker::new));
        EntityClassLookup.add(Vex.class, new EntityClass<Vex>(org.bukkit.entity.Vex.class, CraftVex.class, CraftVex::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Vindicator.class, new EntityClass<net.minecraft.world.entity.monster.Vindicator>(Vindicator.class, CraftVindicator.class, CraftVindicator::new));
        EntityClassLookup.add(Illusioner.class, new EntityClass<Illusioner>(org.bukkit.entity.Illusioner.class, CraftIllusioner.class, CraftIllusioner::new));
        EntityClassLookup.add(Creeper.class, new EntityClass<Creeper>(org.bukkit.entity.Creeper.class, CraftCreeper.class, CraftCreeper::new));
        EntityClassLookup.add(Skeleton.class, new EntityClass<Skeleton>(org.bukkit.entity.Skeleton.class, CraftSkeleton.class, CraftSkeleton::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Spider.class, new EntityClass<net.minecraft.world.entity.monster.Spider>(Spider.class, CraftSpider.class, CraftSpider::new));
        EntityClassLookup.add(Giant.class, new EntityClass<Giant>(org.bukkit.entity.Giant.class, CraftGiant.class, CraftGiant::new));
        EntityClassLookup.add(Zombie.class, new EntityClass<Zombie>(org.bukkit.entity.Zombie.class, CraftZombie.class, CraftZombie::new));
        EntityClassLookup.add(Slime.class, new EntityClass<Slime>(org.bukkit.entity.Slime.class, CraftSlime.class, CraftSlime::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Ghast.class, new EntityClass<net.minecraft.world.entity.monster.Ghast>(Ghast.class, CraftGhast.class, CraftGhast::new));
        EntityClassLookup.add(ZombifiedPiglin.class, new EntityClass<ZombifiedPiglin>(PigZombie.class, CraftPigZombie.class, CraftPigZombie::new));
        EntityClassLookup.add(EnderMan.class, new EntityClass<EnderMan>(Enderman.class, CraftEnderman.class, CraftEnderman::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.CaveSpider.class, new EntityClass<net.minecraft.world.entity.monster.CaveSpider>(CaveSpider.class, CraftCaveSpider.class, CraftCaveSpider::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Silverfish.class, new EntityClass<net.minecraft.world.entity.monster.Silverfish>(Silverfish.class, CraftSilverfish.class, CraftSilverfish::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Blaze.class, new EntityClass<net.minecraft.world.entity.monster.Blaze>(Blaze.class, CraftBlaze.class, CraftBlaze::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.MagmaCube.class, new EntityClass<net.minecraft.world.entity.monster.MagmaCube>(MagmaCube.class, CraftMagmaCube.class, CraftMagmaCube::new));
        EntityClassLookup.add(WitherBoss.class, new EntityClass<WitherBoss>(Wither.class, CraftWither.class, CraftWither::new));
        EntityClassLookup.add(net.minecraft.world.entity.ambient.Bat.class, new EntityClass<net.minecraft.world.entity.ambient.Bat>(Bat.class, CraftBat.class, CraftBat::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Witch.class, new EntityClass<net.minecraft.world.entity.monster.Witch>(Witch.class, CraftWitch.class, CraftWitch::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Endermite.class, new EntityClass<net.minecraft.world.entity.monster.Endermite>(Endermite.class, CraftEndermite.class, CraftEndermite::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Guardian.class, new EntityClass<net.minecraft.world.entity.monster.Guardian>(Guardian.class, CraftGuardian.class, CraftGuardian::new));
        EntityClassLookup.add(Shulker.class, new EntityClass<Shulker>(org.bukkit.entity.Shulker.class, CraftShulker.class, CraftShulker::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.Pig.class, new EntityClass<net.minecraft.world.entity.animal.Pig>(Pig.class, CraftPig.class, CraftPig::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.Sheep.class, new EntityClass<net.minecraft.world.entity.animal.Sheep>(Sheep.class, CraftSheep.class, CraftSheep::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.Cow.class, new EntityClass<net.minecraft.world.entity.animal.Cow>(Cow.class, CraftCow.class, CraftCow::new));
        EntityClassLookup.add(Chicken.class, new EntityClass<Chicken>(org.bukkit.entity.Chicken.class, CraftChicken.class, CraftChicken::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.Squid.class, new EntityClass<net.minecraft.world.entity.animal.Squid>(Squid.class, CraftSquid.class, CraftSquid::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.Wolf.class, new EntityClass<net.minecraft.world.entity.animal.Wolf>(Wolf.class, CraftWolf.class, CraftWolf::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.MushroomCow.class, new EntityClass<net.minecraft.world.entity.animal.MushroomCow>(MushroomCow.class, CraftMushroomCow.class, CraftMushroomCow::new));
        EntityClassLookup.add(SnowGolem.class, new EntityClass<SnowGolem>(Snowman.class, CraftSnowman.class, CraftSnowman::new));
        EntityClassLookup.add(Ocelot.class, new EntityClass<Ocelot>(org.bukkit.entity.Ocelot.class, CraftOcelot.class, CraftOcelot::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.IronGolem.class, new EntityClass<net.minecraft.world.entity.animal.IronGolem>(IronGolem.class, CraftIronGolem.class, CraftIronGolem::new));
        EntityClassLookup.add(Horse.class, new EntityClass<Horse>(org.bukkit.entity.Horse.class, CraftHorse.class, CraftHorse::new));
        EntityClassLookup.add(Rabbit.class, new EntityClass<Rabbit>(org.bukkit.entity.Rabbit.class, CraftRabbit.class, CraftRabbit::new));
        EntityClassLookup.add(PolarBear.class, new EntityClass<PolarBear>(org.bukkit.entity.PolarBear.class, CraftPolarBear.class, CraftPolarBear::new));
        EntityClassLookup.add(Llama.class, new EntityClass<Llama>(org.bukkit.entity.Llama.class, CraftLlama.class, CraftLlama::new));
        EntityClassLookup.add(Parrot.class, new EntityClass<Parrot>(org.bukkit.entity.Parrot.class, CraftParrot.class, CraftParrot::new));
        EntityClassLookup.add(Villager.class, new EntityClass<Villager>(org.bukkit.entity.Villager.class, CraftVillager.class, CraftVillager::new));
        EntityClassLookup.add(Turtle.class, new EntityClass<Turtle>(org.bukkit.entity.Turtle.class, CraftTurtle.class, CraftTurtle::new));
        EntityClassLookup.add(Phantom.class, new EntityClass<Phantom>(org.bukkit.entity.Phantom.class, CraftPhantom.class, CraftPhantom::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.Cod.class, new EntityClass<net.minecraft.world.entity.animal.Cod>(Cod.class, CraftCod.class, CraftCod::new));
        EntityClassLookup.add(Salmon.class, new EntityClass<Salmon>(org.bukkit.entity.Salmon.class, CraftSalmon.class, CraftSalmon::new));
        EntityClassLookup.add(Pufferfish.class, new EntityClass<Pufferfish>(PufferFish.class, CraftPufferFish.class, CraftPufferFish::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.TropicalFish.class, new EntityClass<net.minecraft.world.entity.animal.TropicalFish>(TropicalFish.class, CraftTropicalFish.class, CraftTropicalFish::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Drowned.class, new EntityClass<net.minecraft.world.entity.monster.Drowned>(Drowned.class, CraftDrowned.class, CraftDrowned::new));
        EntityClassLookup.add(Dolphin.class, new EntityClass<Dolphin>(org.bukkit.entity.Dolphin.class, CraftDolphin.class, CraftDolphin::new));
        EntityClassLookup.add(Cat.class, new EntityClass<Cat>(org.bukkit.entity.Cat.class, CraftCat.class, CraftCat::new));
        EntityClassLookup.add(Panda.class, new EntityClass<Panda>(org.bukkit.entity.Panda.class, CraftPanda.class, CraftPanda::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Pillager.class, new EntityClass<net.minecraft.world.entity.monster.Pillager>(Pillager.class, CraftPillager.class, CraftPillager::new));
        EntityClassLookup.add(Ravager.class, new EntityClass<Ravager>(org.bukkit.entity.Ravager.class, CraftRavager.class, CraftRavager::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.horse.TraderLlama.class, new EntityClass<net.minecraft.world.entity.animal.horse.TraderLlama>(TraderLlama.class, CraftTraderLlama.class, CraftTraderLlama::new));
        EntityClassLookup.add(net.minecraft.world.entity.npc.WanderingTrader.class, new EntityClass<net.minecraft.world.entity.npc.WanderingTrader>(WanderingTrader.class, CraftWanderingTrader.class, CraftWanderingTrader::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.Fox.class, new EntityClass<net.minecraft.world.entity.animal.Fox>(Fox.class, CraftFox.class, CraftFox::new));
        EntityClassLookup.add(Bee.class, new EntityClass<Bee>(org.bukkit.entity.Bee.class, CraftBee.class, CraftBee::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.hoglin.Hoglin.class, new EntityClass<net.minecraft.world.entity.monster.hoglin.Hoglin>(Hoglin.class, CraftHoglin.class, CraftHoglin::new));
        EntityClassLookup.add(Piglin.class, new EntityClass<Piglin>(org.bukkit.entity.Piglin.class, CraftPiglin.class, CraftPiglin::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Strider.class, new EntityClass<net.minecraft.world.entity.monster.Strider>(Strider.class, CraftStrider.class, CraftStrider::new));
        EntityClassLookup.add(net.minecraft.world.entity.monster.Zoglin.class, new EntityClass<net.minecraft.world.entity.monster.Zoglin>(Zoglin.class, CraftZoglin.class, CraftZoglin::new));
        EntityClassLookup.add(PiglinBrute.class, new EntityClass<PiglinBrute>(org.bukkit.entity.PiglinBrute.class, CraftPiglinBrute.class, CraftPiglinBrute::new));
        EntityClassLookup.add(Axolotl.class, new EntityClass<Axolotl>(org.bukkit.entity.Axolotl.class, CraftAxolotl.class, CraftAxolotl::new));
        EntityClassLookup.add(net.minecraft.world.entity.GlowSquid.class, new EntityClass<net.minecraft.world.entity.GlowSquid>(GlowSquid.class, CraftGlowSquid.class, CraftGlowSquid::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.goat.Goat.class, new EntityClass<net.minecraft.world.entity.animal.goat.Goat>(Goat.class, CraftGoat.class, CraftGoat::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.allay.Allay.class, new EntityClass<net.minecraft.world.entity.animal.allay.Allay>(Allay.class, CraftAllay.class, CraftAllay::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.frog.Frog.class, new EntityClass<net.minecraft.world.entity.animal.frog.Frog>(Frog.class, CraftFrog.class, CraftFrog::new));
        EntityClassLookup.add(Tadpole.class, new EntityClass<Tadpole>(org.bukkit.entity.Tadpole.class, CraftTadpole.class, CraftTadpole::new));
        EntityClassLookup.add(Warden.class, new EntityClass<Warden>(org.bukkit.entity.Warden.class, CraftWarden.class, CraftWarden::new));
        EntityClassLookup.add(Camel.class, new EntityClass<Camel>(org.bukkit.entity.Camel.class, CraftCamel.class, CraftCamel::new));
        EntityClassLookup.add(net.minecraft.world.entity.animal.sniffer.Sniffer.class, new EntityClass<net.minecraft.world.entity.animal.sniffer.Sniffer>(Sniffer.class, CraftSniffer.class, CraftSniffer::new));
        EntityClassLookup.add(EnderDragon.class, new EntityClass<EnderDragon>(org.bukkit.entity.EnderDragon.class, CraftEnderDragon.class, CraftEnderDragon::new));
        EntityClassLookup.add(LargeFireball.class, new EntityClass<LargeFireball>(org.bukkit.entity.LargeFireball.class, CraftLargeFireball.class, CraftLargeFireball::new));
        EntityClassLookup.add(SmallFireball.class, new EntityClass<SmallFireball>(org.bukkit.entity.SmallFireball.class, CraftSmallFireball.class, CraftSmallFireball::new));
        EntityClassLookup.add(WitherSkull.class, new EntityClass<WitherSkull>(org.bukkit.entity.WitherSkull.class, CraftWitherSkull.class, CraftWitherSkull::new));
        EntityClassLookup.add(net.minecraft.world.entity.projectile.DragonFireball.class, new EntityClass<net.minecraft.world.entity.projectile.DragonFireball>(DragonFireball.class, CraftDragonFireball.class, CraftDragonFireball::new));
        EntityClassLookup.add(Painting.class, new EntityClass<Painting>(org.bukkit.entity.Painting.class, CraftPainting.class, CraftPainting::new));
        EntityClassLookup.add(net.minecraft.world.entity.decoration.ItemFrame.class, new EntityClass<net.minecraft.world.entity.decoration.ItemFrame>(ItemFrame.class, CraftItemFrame.class, CraftItemFrame::new));
        EntityClassLookup.add(net.minecraft.world.entity.decoration.GlowItemFrame.class, new EntityClass<net.minecraft.world.entity.decoration.GlowItemFrame>(GlowItemFrame.class, CraftGlowItemFrame.class, CraftGlowItemFrame::new));
        EntityClassLookup.add(Arrow.class, new EntityClass<Arrow>(org.bukkit.entity.Arrow.class, CraftTippedArrow.class, CraftTippedArrow::new));
        EntityClassLookup.add(ThrownEnderpearl.class, new EntityClass<ThrownEnderpearl>(EnderPearl.class, CraftEnderPearl.class, CraftEnderPearl::new));
        EntityClassLookup.add(ThrownExperienceBottle.class, new EntityClass<ThrownExperienceBottle>(ThrownExpBottle.class, CraftThrownExpBottle.class, CraftThrownExpBottle::new));
        EntityClassLookup.add(SpectralArrow.class, new EntityClass<SpectralArrow>(org.bukkit.entity.SpectralArrow.class, CraftSpectralArrow.class, CraftSpectralArrow::new));
        EntityClassLookup.add(EndCrystal.class, new EntityClass<EndCrystal>(EnderCrystal.class, CraftEnderCrystal.class, CraftEnderCrystal::new));
        EntityClassLookup.add(ThrownTrident.class, new EntityClass<ThrownTrident>(Trident.class, CraftTrident.class, CraftTrident::new));
        EntityClassLookup.add(LightningBolt.class, new EntityClass<LightningBolt>(LightningStrike.class, CraftLightningStrike.class, CraftLightningStrike::new));
        EntityClassLookup.add(ShulkerBullet.class, new EntityClass<ShulkerBullet>(org.bukkit.entity.ShulkerBullet.class, CraftShulkerBullet.class, CraftShulkerBullet::new));
        EntityClassLookup.add(net.minecraft.world.entity.vehicle.Boat.class, new EntityClass<net.minecraft.world.entity.vehicle.Boat>(Boat.class, CraftBoat.class, CraftBoat::new));
        EntityClassLookup.add(LlamaSpit.class, new EntityClass<LlamaSpit>(org.bukkit.entity.LlamaSpit.class, CraftLlamaSpit.class, CraftLlamaSpit::new));
        EntityClassLookup.add(ChestBoat.class, new EntityClass<ChestBoat>(org.bukkit.entity.ChestBoat.class, CraftChestBoat.class, CraftChestBoat::new));
        EntityClassLookup.add(Marker.class, new EntityClass<Marker>(org.bukkit.entity.Marker.class, CraftMarker.class, CraftMarker::new));
        EntityClassLookup.add(Display.BlockDisplay.class, new EntityClass<Display.BlockDisplay>(BlockDisplay.class, CraftBlockDisplay.class, CraftBlockDisplay::new));
        EntityClassLookup.add(Interaction.class, new EntityClass<Interaction>(org.bukkit.entity.Interaction.class, CraftInteraction.class, CraftInteraction::new));
        EntityClassLookup.add(Display.ItemDisplay.class, new EntityClass<Display.ItemDisplay>(ItemDisplay.class, CraftItemDisplay.class, CraftItemDisplay::new));
        EntityClassLookup.add(Display.TextDisplay.class, new EntityClass<Display.TextDisplay>(TextDisplay.class, CraftTextDisplay.class, CraftTextDisplay::new));
        EntityClassLookup.add(ItemEntity.class, new EntityClass(Item.class, CraftItem.class, EntityClassLookup.convert("CraftItem")));
        EntityClassLookup.add(net.minecraft.world.entity.ExperienceOrb.class, new EntityClass<net.minecraft.world.entity.ExperienceOrb>(ExperienceOrb.class, CraftExperienceOrb.class, CraftExperienceOrb::new));
        EntityClassLookup.add(net.minecraft.world.entity.AreaEffectCloud.class, new EntityClass<net.minecraft.world.entity.AreaEffectCloud>(AreaEffectCloud.class, CraftAreaEffectCloud.class, CraftAreaEffectCloud::new));
        EntityClassLookup.add(ThrownEgg.class, new EntityClass<ThrownEgg>(Egg.class, CraftEgg.class, CraftEgg::new));
        EntityClassLookup.add(LeashFenceKnotEntity.class, new EntityClass<LeashFenceKnotEntity>(LeashHitch.class, CraftLeash.class, CraftLeash::new));
        EntityClassLookup.add(net.minecraft.world.entity.projectile.Snowball.class, new EntityClass<net.minecraft.world.entity.projectile.Snowball>(Snowball.class, CraftSnowball.class, CraftSnowball::new));
        EntityClassLookup.add(EyeOfEnder.class, new EntityClass<EyeOfEnder>(EnderSignal.class, CraftEnderSignal.class, CraftEnderSignal::new));
        EntityClassLookup.add(net.minecraft.world.entity.projectile.ThrownPotion.class, new EntityClass<net.minecraft.world.entity.projectile.ThrownPotion>(ThrownPotion.class, CraftThrownPotion.class, CraftThrownPotion::new));
        EntityClassLookup.add(PrimedTnt.class, new EntityClass<PrimedTnt>(TNTPrimed.class, CraftTNTPrimed.class, CraftTNTPrimed::new));
        EntityClassLookup.add(FallingBlockEntity.class, new EntityClass<FallingBlockEntity>(FallingBlock.class, CraftFallingBlock.class, CraftFallingBlock::new));
        EntityClassLookup.add(FireworkRocketEntity.class, new EntityClass<FireworkRocketEntity>(Firework.class, CraftFirework.class, CraftFirework::new));
        EntityClassLookup.add(net.minecraft.world.entity.projectile.EvokerFangs.class, new EntityClass<net.minecraft.world.entity.projectile.EvokerFangs>(EvokerFangs.class, CraftEvokerFangs.class, CraftEvokerFangs::new));
        EntityClassLookup.add(MinecartCommandBlock.class, new EntityClass<MinecartCommandBlock>(CommandMinecart.class, CraftMinecartCommand.class, CraftMinecartCommand::new));
        EntityClassLookup.add(Minecart.class, new EntityClass<AbstractMinecart>(RideableMinecart.class, CraftMinecartRideable.class, CraftMinecartRideable::new));
        EntityClassLookup.add(MinecartChest.class, new EntityClass<MinecartChest>(StorageMinecart.class, CraftMinecartChest.class, CraftMinecartChest::new));
        EntityClassLookup.add(MinecartFurnace.class, new EntityClass<MinecartFurnace>(PoweredMinecart.class, CraftMinecartFurnace.class, CraftMinecartFurnace::new));
        EntityClassLookup.add(MinecartTNT.class, new EntityClass(ExplosiveMinecart.class, CraftMinecartTNT.class, EntityClassLookup.convert("CraftMinecartTNT")));
        EntityClassLookup.add(MinecartHopper.class, new EntityClass<MinecartHopper>(HopperMinecart.class, CraftMinecartHopper.class, CraftMinecartHopper::new));
        EntityClassLookup.add(MinecartSpawner.class, new EntityClass(SpawnerMinecart.class, EntityClassLookup.forName("CraftMinecartMobSpawner"), EntityClassLookup.convert("CraftMinecartMobSpawner")));
        EntityClassLookup.add(FishingHook.class, new EntityClass<FishingHook>(FishHook.class, CraftFishHook.class, CraftFishHook::new));
        EntityClassLookup.add(ServerPlayer.class, new EntityClass<ServerPlayer>(org.bukkit.entity.Player.class, CraftPlayer.class, CraftPlayer::new));
    }

    private record EntityClass<T extends net.minecraft.world.entity.Entity>(Class<? extends Entity> bukkitClass, Class<? extends CraftEntity> implClass, BiFunction<CraftServer, T, Entity> convert) {
        private EntityClass {
            if (!bukkitClass.isAssignableFrom(implClass)) {
                throw new IllegalArgumentException(String.valueOf(bukkitClass) + " " + String.valueOf(implClass));
            }
        }
    }
}

