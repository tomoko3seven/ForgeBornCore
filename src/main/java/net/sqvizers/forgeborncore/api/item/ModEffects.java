package net.sqvizers.forgeborncore.api.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sqvizers.forgeborncore.api.item.behaviour.StunEffect;
import net.sqvizers.forgeborncore.forgeborncore;


public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, forgeborncore.MOD_ID);

    public static final RegistryObject<MobEffect> STUN = MOB_EFFECTS.register("stun",
            () -> new StunEffect(MobEffectCategory.HARMFUL, 3124687));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
