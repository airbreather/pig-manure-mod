package airbreather.mods.pigmanure;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import net.minecraftforge.common.capabilities.Capability;

import airbreather.mods.airbreathercore.reflect.EntityAccessor;

// extended EntityPig properties used for this mod.
final class PigManureTicks implements IPigManureTicks
{
    private static final Random DefaultRandom = new Random();

    // Not in PigManureConstants because this concept is local to this class.
    private static final String TicksLeftPropertyIdentifier = "TicksLeft";

    private Random random;
    private int ticksLeft;

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger(TicksLeftPropertyIdentifier, this.ticksLeft);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbtTagCompound)
    {
        int savedTicksLeft = nbtTagCompound.getInteger(TicksLeftPropertyIdentifier);
        if (savedTicksLeft <= 0)
        {
            // This happens when we load a pig from a world that didn't have this mod installed.
            this.ResetTicksLeft();
        }
        else
        {
            // This happens when we load a pig from a world that did have this mod installed.
            this.ticksLeft = savedTicksLeft;
        }
    }

    public void init(Entity entity)
    {
        final EntityAccessor entityAccessor = new EntityAccessor(entity);
        this.random = entityAccessor.GetRand().or(DefaultRandom);
        this.ResetTicksLeft();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == PigManureConstants.PigManureTicksCapability;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == PigManureConstants.PigManureTicksCapability ? (T)this : null;
    }

    @Override
    public Random GetRandom()
    {
        return this.random;
    }

    @Override
    public boolean DecrementTicksLeft()
    {
        if (--this.ticksLeft <= 0)
        {
            this.ResetTicksLeft();
            return true;
        }

        return false;
    }

    private void ResetTicksLeft()
    {
        this.ticksLeft = PigManureConstants.LowerBoundTicksBetweenDroppings +
                         this.random.nextInt(PigManureConstants.UpperBoundAdditionalTicksBetweenDroppings);
    }
}
