package airbreather.mods.pigmanure;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import net.minecraftforge.common.util.FakePlayerFactory;
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

            // don't blame me; this is what net.minecraft.init.Bootstrap does.
            BlockDispenser.dispenseBehaviorRegistry.putObject(manureItem, new BehaviorDefaultDispenseItem()
            {
                private boolean dispensed = true;

                /**
                 * Dispense the specified stack, play the dispense sound and spawn particles.
                 */
                @Override
                protected ItemStack dispenseStack(IBlockSource p_82487_1_, ItemStack p_82487_2_)
                {
                    EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.getBlockMetadata());
                    World world = p_82487_1_.getWorld();
                    int i = p_82487_1_.getXInt() + enumfacing.getFrontOffsetX();
                    int j = p_82487_1_.getYInt() + enumfacing.getFrontOffsetY();
                    int k = p_82487_1_.getZInt() + enumfacing.getFrontOffsetZ();

                    if ((world instanceof WorldServer) && ItemDye.applyBonemeal(p_82487_2_, world, i, j, k, FakePlayerFactory.getMinecraft((WorldServer)world)))
                    {
                        if (!world.isRemote)
                        {
                            world.playAuxSFX(2005, i, j, k, 0);
                        }
                    }
                    else
                    {
                        this.dispensed = false;
                    }

                    return p_82487_2_;
                }
                /**
                 * Play the dispense sound from the specified block.
                 */
                @Override
                protected void playDispenseSound(IBlockSource p_82485_1_)
                {
                    if (this.dispensed)
                    {
                        p_82485_1_.getWorld().playAuxSFX(1000, p_82485_1_.getXInt(), p_82485_1_.getYInt(), p_82485_1_.getZInt(), 0);
                    }
                    else
                    {
                        p_82485_1_.getWorld().playAuxSFX(1001, p_82485_1_.getXInt(), p_82485_1_.getYInt(), p_82485_1_.getZInt(), 0);
                    }
                }
            });

            return manureItem;
        }

        return super.CreateItemCore(definition);
    }
}
