package airbreather.mods.pigmanure;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.IEventListener;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

final class PigConstructingEventHandler implements IEventListener
{
    @Override
    public void invoke(Event event)
    {
        checkNotNull(event, "event");
        checkArgument(event instanceof EntityConstructing,
                      "expected EntityConstructing, but got %s.",
                      event.getClass());
        EntityConstructing typedEvent = (EntityConstructing)event;

        typedEvent.entity.registerExtendedProperties(PigManureConstants.ExtendedPigPropertiesIdentifier,
                                                     new PigManureExtendedEntityProperties());
    }
}
