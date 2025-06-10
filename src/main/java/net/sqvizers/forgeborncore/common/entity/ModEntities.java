package net.sqvizers.forgeborncore.common.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sqvizers.forgeborncore.common.entity.projectile.BulletEntity;
import net.sqvizers.forgeborncore.forgeborncore;

@Mod.EventBusSubscriber(modid = forgeborncore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, forgeborncore.MOD_ID);

    public static final RegistryObject<EntityType<BulletEntity>> BULLET = ENTITIES.register("bullet", () ->
            EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
                    .sized(0.3125f, 0.3125f)
                    .setUpdateInterval(2)
                    .setTrackingRange(64)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(new ResourceLocation(forgeborncore.MOD_ID, "bullet").toString())
    );

}
