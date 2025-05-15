package net.sqvizers.forgeborncore.common.data;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.sqvizers.forgeborncore.data.recipe.MiscRecipes;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;
import static net.sqvizers.forgeborncore.gtbrucke.FBRecipeTypes.SAWMILLRECIPES;

public class FBCRecipes {

    /*private static void registerSawmillRecipes(Consumer<FinishedRecipe> provider) {
        SAWMILLRECIPES.recipeBuilder("wood_to_").inputItems(ingot, Iron)
                .inputItems(gem, Coal, 2).outputItems(ingot, Steel).outputItems(dustTiny, DarkAsh, 2)
                .duration((int) (1800 * 0.75f)).inputFluids(Creosote.getFluid(250))
                .save(provider);
    }*/

    public static void init(Consumer<FinishedRecipe> provider) {
        MiscRecipes.init(provider);
    }
}
