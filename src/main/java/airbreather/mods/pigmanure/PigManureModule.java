package airbreather.mods.pigmanure;

import airbreather.mods.airbreathercore.CustomConfiguration;
import airbreather.mods.airbreathercore.item.ItemRegistrar;
import airbreather.mods.airbreathercore.item.ItemRegistry;
import airbreather.mods.airbreathercore.mod.ModuleBase;

final class PigManureModule extends ModuleBase
{
    private PigManureConfigurationAdapter customConfiguration;

    public PigManureModule()
    {
        super();

        PigManureItemConfiguration itemConfiguration = new PigManureItemConfiguration();
        PigManureRecipeConfiguration recipeConfiguration = new PigManureRecipeConfiguration();

        ItemRegistry itemRegistry = this.GetItemRegistry();

        PigUpdateEventHandler pigUpdateHandler = new PigUpdateEventHandler(itemRegistry);
        PigManureEventConfiguration eventConfiguration = new PigManureEventConfiguration(pigUpdateHandler);

        this.customConfiguration = new PigManureConfigurationAdapter(itemConfiguration,
                                                                     recipeConfiguration,
                                                                     eventConfiguration);
    }

    @Override
    public ItemRegistrar GetItemRegistrar() { return new PigManureItemRegistrar(); }

    @Override
    public CustomConfiguration GetCustomConfiguration() { return this.customConfiguration; }
}
