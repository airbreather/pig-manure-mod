package airbreather.mods.pigmanure;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import net.minecraftforge.oredict.OreDictionary;

import airbreather.mods.airbreathercore.item.ItemDefinition;
import airbreather.mods.airbreathercore.item.ItemRegistrarBase;

import static com.google.common.base.Preconditions.checkNotNull;

// A helper class to register all the items added by this mod.
final class PigManureItemRegistrar extends ItemRegistrarBase
{
    @Override
    public Item CreateItemCore(ItemDefinition definition)
    {
        checkNotNull(definition, "definition");
        if (definition.equals(PigManureConstants.ManureItemDefinition))
        {
            ItemManure manureItem = new ItemManure();
            manureItem.setCreativeTab(CreativeTabs.tabMisc);

            // TODO: abstract this out a little bit.
            OreDictionary.registerOre("dyeBrown", manureItem);

            return manureItem;
        }

        return super.CreateItemCore(definition);
    }
}
