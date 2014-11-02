package airbreather.mods.pigmanure;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import airbreather.mods.airbreathercore.reflect.EntityAccessor;

// extended EntityPig properties used for this mod.
final class PigManureExtendedEntityProperties implements IExtendedEntityProperties
{
    private static final Random DefaultRandom = new Random();

    // Not in PigManureConstants because this concept is local to this class.
    private static final String TicksLeftPropertyIdentifier = "TicksLeft";

    private Random random;
    private int ticksLeft;

    @Override
    public void saveNBTData(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setInteger(TicksLeftPropertyIdentifier, this.ticksLeft);
    }

    @Override
    public void loadNBTData(NBTTagCompound nbtTagCompound)
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

    @Override
    public void init(Entity entity, World world)
    {
        final EntityAccessor entityAccessor = new EntityAccessor(entity);
        this.random = entityAccessor.GetRand().or(DefaultRandom);
        this.ResetTicksLeft();
    }

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
