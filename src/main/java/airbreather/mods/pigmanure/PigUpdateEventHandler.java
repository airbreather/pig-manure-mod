package airbreather.mods.pigmanure;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.Item;
import net.minecraft.init.SoundEvents;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.IEventListener;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import airbreather.mods.airbreathercore.item.ItemRegistry;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

final class PigUpdateEventHandler implements IEventListener
{
    private final ItemRegistry itemRegistry;

    public PigUpdateEventHandler(ItemRegistry itemRegistry)
    {
        this.itemRegistry = checkNotNull(itemRegistry);
    }

    @Override
    public void invoke(Event event)
    {
        checkNotNull(event, "event");
        checkArgument(event instanceof LivingUpdateEvent,
                      "expected LivingUpdateEvent, but got %s.",
                      event.getClass());
        LivingUpdateEvent typedEvent = (LivingUpdateEvent)event;

        Entity entity = typedEvent.getEntity();
        if (entity.worldObj.isRemote)
        {
            return;
        }

        if (!(entity instanceof EntityPig))
        {
            return;
        }

        EntityPig typedEntity = (EntityPig)entity;

        if (typedEntity.isChild())
        {
            // OK yes, I get it, children do in fact poop.
            // This is more a practical / balance consideration than a realism thing.
            return;
        }

        IPigManureTicks eep = typedEntity.getCapability(PigManureConstants.PigManureTicksCapability, null);

        if (eep.DecrementTicksLeft())
        {
            Random rand = eep.GetRandom();
            float firstValue = rand.nextFloat();
            float secondValue = rand.nextFloat();
            float pitch = firstValue - secondValue * 0.2F + 1.0F;

            typedEntity.playSound(SoundEvents.entity_chicken_egg, 1.0F, pitch);
            final Item itemToDrop = this.itemRegistry.FetchItem(PigManureConstants.ManureItemDefinition);
            typedEntity.dropItem(itemToDrop, 1);
        }
    }
}
