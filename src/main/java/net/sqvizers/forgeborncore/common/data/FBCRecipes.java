package net.sqvizers.forgeborncore.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.sqvizers.forgeborncore.data.recipe.MiscRecipes;

import java.util.function.Consumer;

public class FBCRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        MiscRecipes.init(provider);
    }
}
