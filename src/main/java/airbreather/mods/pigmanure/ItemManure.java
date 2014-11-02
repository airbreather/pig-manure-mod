package airbreather.mods.pigmanure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

final class ItemManure extends Item
{
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }

        if (ItemDye.applyBonemeal(par1ItemStack, par3World, par4, par5, par6, par2EntityPlayer))
        {
            if (!par3World.isRemote)
            {
                par3World.playAuxSFX(2005, par4, par5, par6, 0);
            }

            return true;
        }

        return false;
    }
}
