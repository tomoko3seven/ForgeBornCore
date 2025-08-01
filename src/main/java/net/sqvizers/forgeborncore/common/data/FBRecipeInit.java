package net.sqvizers.forgeborncore.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.sqvizers.forgeborncore.data.recipe.FBComponentRecipes;

import java.util.function.Consumer;

public class FBRecipeInit {public static void init(Consumer<FinishedRecipe> provider) {
        FBComponentRecipes.init(provider);
}
}