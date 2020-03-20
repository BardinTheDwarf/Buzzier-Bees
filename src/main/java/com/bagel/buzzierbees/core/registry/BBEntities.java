package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.client.render.GrizzlyBearRenderer;
import com.bagel.buzzierbees.client.render.HiveBoatRenderer;
import com.bagel.buzzierbees.client.render.HoneySlimeRenderer;
import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;
import com.bagel.buzzierbees.common.entities.HiveBoatEntity;
import com.bagel.buzzierbees.common.entities.HoneySlimeEntity;
import com.bagel.buzzierbees.core.config.BBConfig;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBEntities
{
	public static EntityType<HoneySlimeEntity>  HONEY_SLIME;
	public static EntityType<HiveBoatEntity>        BOAT;
	public static EntityType<GrizzlyBearEntity>        GRIZZLY_BEAR;
	
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
    {
        HONEY_SLIME = EntityType.Builder.<HoneySlimeEntity>create(HoneySlimeEntity::new, EntityClassification.CREATURE).size(1.02F, 1.02F).build("buzzierbees:honey_slime");
        HONEY_SLIME.setRegistryName("honey_slime");
        ForgeRegistries.ENTITIES.register(HONEY_SLIME);
        
        BOAT = EntityType.Builder.<HiveBoatEntity>create(HiveBoatEntity::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).build("buzzierbees:boat");
        BOAT.setRegistryName("boat");
        ForgeRegistries.ENTITIES.register(BOAT);
        
        GRIZZLY_BEAR = EntityType.Builder.<GrizzlyBearEntity>create(GrizzlyBearEntity::new, EntityClassification.CREATURE).size(1.4F, 1.4F).build("buzzierbees:grizzly_bear");
        GRIZZLY_BEAR.setRegistryName("grizzly_bear");
        ForgeRegistries.ENTITIES.register(GRIZZLY_BEAR);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends HoneySlimeEntity>)HONEY_SLIME, HoneySlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends HiveBoatEntity>)BOAT, HiveBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends GrizzlyBearEntity>)GRIZZLY_BEAR, GrizzlyBearRenderer::new);
    }
    
    public static void addEntitySpawns() {
		EntitySpawnPlacementRegistry.register(HONEY_SLIME, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HoneySlimeEntity::honeySlimeCondition);
		if (BBConfig.spawnHoneySlimes) Biomes.FLOWER_FOREST.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(HONEY_SLIME, 6, 1, 1));
	}
}
