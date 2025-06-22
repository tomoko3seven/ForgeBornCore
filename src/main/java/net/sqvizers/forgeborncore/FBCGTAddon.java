package net.sqvizers.forgeborncore;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTFluids;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import earth.terrarium.adastra.common.registry.ModFluids;
import net.sqvizers.forgeborncore.api.registries.FBCRegistries;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;
import net.sqvizers.forgeborncore.common.FBItems;
import net.sqvizers.forgeborncore.common.data.tag.FBTagPrefix;

import java.util.function.Consumer;

import static net.sqvizers.forgeborncore.forgeborncore.MOD_ID;

@GTAddon
public class FBCGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return FBCRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return MOD_ID;
    }

    @Override
    public void collectMaterialCasings(MaterialCasingCollectionEvent event) {
        IGTAddon.super.collectMaterialCasings(event);
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        net.sqvizers.forgeborncore.common.data.FBCRecipes.init(provider);
    }

    @Override
    public boolean requiresHighTier() {
        return true;
    }

    @Override
    public void registerCovers() {
        FBItems.init();
        GTMaterials.Oxygen.getProperty(PropertyKey.FLUID).getStorage().store(FluidStorageKeys.GAS, ModFluids.OXYGEN, null);
        GTMaterials.Hydrogen.getProperty(PropertyKey.FLUID).getStorage().store(FluidStorageKeys.GAS, ModFluids.HYDROGEN, null);
        GTFluids.handleNonMaterialFluids(GTMaterials.Oil, ModFluids.OIL);
    }

    @Override
    public void registerTagPrefixes() {
        FBTagPrefix.init();
    }
}
