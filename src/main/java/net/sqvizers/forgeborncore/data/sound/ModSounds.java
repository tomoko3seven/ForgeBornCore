package net.sqvizers.forgeborncore.data.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sqvizers.forgeborncore.forgeborncore;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, forgeborncore.MOD_ID);
    public static RegistryObject<SoundEvent> gun = SOUNDS.register("gun",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(forgeborncore.MOD_ID, "item.gun.shoot")));

    public static RegistryObject<SoundEvent> pulse_shotgun = SOUNDS.register("item.shotgun.shoot",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("shotgun")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
