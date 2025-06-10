package net.sqvizers.forgeborncore.client;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.sqvizers.forgeborncore.api.item.gun.BulletItem;
import net.sqvizers.forgeborncore.common.FBItems;
import net.sqvizers.forgeborncore.common.entity.ModEntities;

import net.sqvizers.forgeborncore.forgeborncore;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = forgeborncore.MOD_ID, value = Dist.CLIENT)
public class ClientStuff {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        //Same renderer as potions
        event.registerEntityRenderer(ModEntities.BULLET.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void clientStuff(final FMLClientSetupEvent event) {
        ItemProperties.register(FBItems.SIMPLE_BULLET.get(), new ResourceLocation(forgeborncore.MOD_ID, "shot"), (stack, world, entity, someint) -> BulletItem.isShot(stack) ? 1 : 0);
    }
}
