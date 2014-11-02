package airbreather.mods.pigmanure;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import airbreather.mods.airbreathercore.CustomConfigurationBase;
import airbreather.mods.airbreathercore.event.EventConfiguration;
import airbreather.mods.airbreathercore.item.ItemConfiguration;
import airbreather.mods.airbreathercore.recipe.RecipeConfiguration;

import static com.google.common.base.Preconditions.checkNotNull;

// Implements CustomConfiguration using the standard Forge configuration pattern, given a File.
final class PigManureConfigurationAdapter extends CustomConfigurationBase
{
    private final PigManureItemConfiguration itemConfiguration;
    private final PigManureRecipeConfiguration recipeConfiguration;
    private final PigManureEventConfiguration eventConfiguration;

    public PigManureConfigurationAdapter(PigManureItemConfiguration itemConfiguration,
                                         PigManureRecipeConfiguration recipeConfiguration,
                                         PigManureEventConfiguration eventConfiguration)
    {
        this.itemConfiguration = checkNotNull(itemConfiguration, "itemConfiguration");
        this.recipeConfiguration = checkNotNull(recipeConfiguration, "recipeConfiguration");
        this.eventConfiguration = checkNotNull(eventConfiguration, "eventConfiguration");
    }

    @Override
    public void Initialize(File configurationFile)
    {
        checkNotNull(configurationFile, "configurationFile");
        Configuration forgeConfiguration = new Configuration(configurationFile);
        forgeConfiguration.load();

        if (ShouldEnableBrickSmeltingRecipe(forgeConfiguration))
        {
            this.recipeConfiguration.EnableBrickSmeltingRecipe();
        }

        if (ShouldEnableManureDrops(forgeConfiguration))
        {
            this.eventConfiguration.EnableManureDrops();
        }

        // If I wanted to make the fact that manure can be used to dye things brown,
        // I'd add something weird here.  I don't think there's an important case where that would be relevant.

        // TODO: Skip saving if we aren't in "create-initial" mode.
        forgeConfiguration.save();
    }

    @Override
    public ItemConfiguration GetItemConfiguration()
    {
        return this.itemConfiguration;
    }

    @Override
    public RecipeConfiguration GetRecipeConfiguration()
    {
        return this.recipeConfiguration;
    }

    @Override
    public EventConfiguration GetEventConfiguration()
    {
        return this.eventConfiguration;
    }

    private static boolean ShouldEnableManureDrops(Configuration forgeConfiguration)
    {
        String enableManureDrops = "enableManureDrops";
        boolean enableManureDropsDefault = true;
        String enableManureDropsComment = "Allow pigs to drop manure?  true/false (true is the default)" +
                                          Configuration.NEW_LINE +
                                          "This is how you would gracefully disable this mod after having it around " +
                                          "for a while, without players losing what they already have.";

        Property enableManureDropsProperty = forgeConfiguration.get(Configuration.CATEGORY_GENERAL,
                                                                    enableManureDrops,
                                                                    enableManureDropsDefault,
                                                                    enableManureDropsComment);
        return enableManureDropsProperty.getBoolean(enableManureDropsDefault);
    }

    private static boolean ShouldEnableBrickSmeltingRecipe(Configuration forgeConfiguration)
    {
        String enableBrickSmeltingRecipe = "enableBrickSmeltingRecipe";
        boolean enableBrickSmeltingRecipeDefault = true;
        String enableBrickSmeltingRecipeComment = "Enable the Manure --> Brick smelting recipe?  true/false (true is the default)";

        Property enableBrickSmeltingRecipeProperty = forgeConfiguration.get(Configuration.CATEGORY_GENERAL,
                                                                            enableBrickSmeltingRecipe,
                                                                            enableBrickSmeltingRecipeDefault,
                                                                            enableBrickSmeltingRecipeComment);
        return enableBrickSmeltingRecipeProperty.getBoolean(enableBrickSmeltingRecipeDefault);
    }
}
