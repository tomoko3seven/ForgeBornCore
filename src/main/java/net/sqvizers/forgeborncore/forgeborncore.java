package net.sqvizers.forgeborncore;

import net.minecraftforge.eventbus.api.IEventBus;
import net.sqvizers.forgeborncore.api.registries.FBCRegistries;
import net.sqvizers.forgeborncore.common.FBItems;
import net.sqvizers.forgeborncore.common.data.FBCCreativeModeTabs;
import net.sqvizers.forgeborncore.common.data.FBCMachines;
import net.sqvizers.forgeborncore.common.entity.ModEntities;
import net.sqvizers.forgeborncore.data.FBCDatagen;
import net.sqvizers.forgeborncore.data.material.ForgeMaterials;
import net.sqvizers.forgeborncore.gtbrucke.FBRecipeTypes;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.config.ConfigHolder;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

@Mod(forgeborncore.MOD_ID)
public class forgeborncore {

    public static final String MOD_ID = "forgeborncore", NAME = "ForgeBorn Core";;
    public static final Logger LOGGER = LoggerFactory.getLogger(forgeborncore.class);
    public static MaterialRegistry MATERIAL_REGISTRY;

    public forgeborncore() {
        forgeborncore.init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITIES.register(modEventBus);
        modEventBus.register(this);
        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);

        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);
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

    public void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        FBRecipeTypes.init();
    }

    /*
     * @SubscribeEvent
     * public void modifyExistingMaterials(PostMaterialEvent event) {
     * ForgeMaterials.modifyMaterials();
     * }
     */
}
