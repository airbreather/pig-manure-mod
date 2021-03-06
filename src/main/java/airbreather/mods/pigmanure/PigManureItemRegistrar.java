package airbreather.mods.pigmanure;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.oredict.OreDictionary;

import airbreather.mods.airbreathercore.item.ItemDefinition;
import airbreather.mods.airbreathercore.item.ItemRegistrarBase;
import airbreather.mods.airbreathercore.item.ItemRegistry;

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
            manureItem.setCreativeTab(CreativeTabs.MISC);

            // don't blame me; this is what net.minecraft.init.Bootstrap does.
            BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(manureItem, new Bootstrap.BehaviorDispenseOptional()
            {
                /**
                 * Dispense the specified stack, play the dispense sound and spawn particles.
                 */
                protected ItemStack dispenseStack(IBlockSource source, ItemStack stack)
                {
                    this.field_190911_b = true;
                    World world = source.getWorld();
                    BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(BlockDispenser.FACING));

                    if (ItemDye.applyBonemeal(stack, world, blockpos))
                    {
                        if (!world.isRemote)
                        {
                            world.playEvent(2005, blockpos, 0);
                        }
                    }
                    else
                    {
                        this.field_190911_b = false;
                    }

                    return stack;
                }
            });

            return manureItem;
        }

        return super.CreateItemCore(definition);
    }

    @Override
    public void AfterItemRegistered(ItemDefinition definition, Item item, ItemRegistry registry)
    {
        checkNotNull(definition, "definition");
        checkNotNull(item, "item");

        if (definition.equals(PigManureConstants.ManureItemDefinition))
        {
            // TODO: abstract this out a little bit.
            OreDictionary.registerOre("dyeBrown", item);
        }
    }
}
