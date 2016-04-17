package airbreather.mods.pigmanure;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.event.AttachCapabilitiesEvent;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

final class AttachCapabilitiesEventHandler
{
    @SubscribeEvent
    public void invoke(AttachCapabilitiesEvent.Entity event)
    {
        checkNotNull(event, "event");
        Entity entity = event.getEntity();
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

        PigManureTicks pmeep = new PigManureTicks();
        pmeep.init(event.getEntity());
        event.addCapability(PigManureConstants.PigManureTicksIdentifier, pmeep);
    }
}
