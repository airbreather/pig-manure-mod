package airbreather.mods.pigmanure;

import com.google.common.collect.ImmutableList;

import airbreather.mods.airbreathercore.item.ItemConfiguration;
import airbreather.mods.airbreathercore.item.ItemDefinition;

// Holds item-related configuration information, specific to PigManure.
final class PigManureItemConfiguration implements ItemConfiguration
{
    @Override
    public Iterable<ItemDefinition> GetItemDefinitionsForNewItems()
    {
        return ImmutableList.of
        (
            PigManureConstants.ManureItemDefinition
        );
    }
}
