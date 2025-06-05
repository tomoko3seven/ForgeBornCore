package net.sqvizers.forgeborncore.common.data;

import net.sqvizers.forgeborncore.data.recipe.MiscRecipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class FBCRecipes {

    /*
     * private static void registerSawmillRecipes(Consumer<FinishedRecipe> provider) {
     * SAWMILLRECIPES.recipeBuilder("wood_to_").inputItems(ingot, Iron)
     * .inputItems(gem, Coal, 2).outputItems(ingot, Steel).outputItems(dustTiny, DarkAsh, 2)
     * .duration((int) (1800 * 0.75f)).inputFluids(Creosote.getFluid(250))
     * .save(provider);
     * }
     */

    public static void init(Consumer<FinishedRecipe> provider) {
        MiscRecipes.init(provider);
    }
}
