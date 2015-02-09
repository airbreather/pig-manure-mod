package airbreather.mods.pigmanure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

final class ItemManure extends Item
{
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        // can this player edit this block?
        if (!playerIn.func_175151_a(pos.offset(side), side, stack))
        {
            return false;
        }

        if (ItemDye.applyBonemeal(stack, worldIn, pos, playerIn))
        {
            if (!worldIn.isRemote)
            {
                worldIn.playAuxSFX(2005, pos, 0);
            }

            return true;
        }

        return false;
    }
}
