package airbreather.mods.pigmanure;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.common.capabilities.ICapabilitySerializable;

interface IPigManureTicks extends ICapabilitySerializable<NBTTagCompound>
{
    Random GetRandom();
    boolean DecrementTicksLeft();
}
