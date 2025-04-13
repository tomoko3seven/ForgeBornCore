package net.sqvizers.forgeborncore;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.config.ConfigHolder;

import com.lowdragmc.lowdraglib.Platform;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sqvizers.forgeborncore.api.registries.FBCRegistries;
import net.sqvizers.forgeborncore.common.FBItems;
import net.sqvizers.forgeborncore.common.data.FBCCreativeModeTabs;
import net.sqvizers.forgeborncore.common.data.FBCMachines;
import net.sqvizers.forgeborncore.data.FBCDatagen;

import net.sqvizers.forgeborncore.data.material.ForgeMaterials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(forgeborncore.MOD_ID)
public class forgeborncore {

    public static final String MOD_ID = "forgeborncore", NAME = "ForgeBorn Core";;
    public static final Logger LOGGER = LoggerFactory.getLogger(forgeborncore.class);
    public static MaterialRegistry MATERIAL_REGISTRY;

    public forgeborncore() {
        forgeborncore.init();
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);

        bus.addGenericListener(MachineDefinition.class, this::registerMachines);


    }

    public static void init() {
        ConfigHolder.init();
        FBCRegistries.REGISTRATE.registerRegistrate();
        FBCCreativeModeTabs.init();
        FBItems.init();
        FBCDatagen.init();

    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @SubscribeEvent
    public void registerMaterialRegistry(MaterialRegistryEvent event) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(forgeborncore.MOD_ID);
    }

    @SubscribeEvent
    public void registerMaterials(MaterialEvent event) {
        ForgeMaterials.register();
    }

    @SubscribeEvent
    public void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        FBCMachines.init();
    }

    /*@SubscribeEvent
    public void modifyExistingMaterials(PostMaterialEvent event) {
        ForgeMaterials.modifyMaterials();
    }*/

}