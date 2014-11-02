package airbreather.mods.pigmanure;

import com.google.common.collect.ImmutableList;

import airbreather.mods.airbreathercore.item.ItemDefinition;
import airbreather.mods.airbreathercore.recipe.Recipe;
import airbreather.mods.airbreathercore.recipe.RecipeConfiguration;
import airbreather.mods.airbreathercore.recipe.RecipeResult;
import airbreather.mods.airbreathercore.recipe.SmeltingRecipe;

final class PigManureRecipeConfiguration implements RecipeConfiguration
{
    private boolean enableBrickSmelting = false;

    public void EnableBrickSmeltingRecipe()
    {
        this.enableBrickSmelting = true;
    }

    @Override
    public Iterable<Recipe> GetRecipes()
    {
        ImmutableList.Builder<Recipe> resultBuilder = ImmutableList.builder();

        if (this.enableBrickSmelting)
        {
            ItemDefinition manure = PigManureConstants.ManureItemDefinition;
            ItemDefinition brick = PigManureConstants.BrickItemDefinition;
            RecipeResult brickResult = new RecipeResult(brick);

            // Smelt Manure --> Brick
            // (0.3 experience, same as clay_ball --> brick)
            final float ManureSmeltingExperience = 0.3F;
            Recipe brickRecipe = new SmeltingRecipe(brickResult, manure, ManureSmeltingExperience);
            resultBuilder.add(brickRecipe);
        }

        return resultBuilder.build();
    }
}
