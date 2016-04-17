package airbreather.mods.pigmanure;

import com.google.common.collect.ImmutableList;

import net.minecraftforge.fml.common.eventhandler.IEventListener;

import airbreather.mods.airbreathercore.event.EventConfiguration;
import airbreather.mods.airbreathercore.event.EventType;

import static com.google.common.base.Preconditions.checkNotNull;

// Holds event-related configuration information, specific to Pig Manure.
final class PigManureEventConfiguration implements EventConfiguration
{
    private final PigUpdateEventHandler pigUpdateHandler;

    private boolean enableManureDrops = false;

    public PigManureEventConfiguration(PigUpdateEventHandler pigUpdateHandler)
    {
        this.pigUpdateHandler = checkNotNull(pigUpdateHandler, "pigUpdateHandler");
    }

    public void EnableManureDrops()
    {
        this.enableManureDrops = true;
    }

    @Override
    public Iterable<EventType> GetRecognizedEventTypes()
    {
        return ImmutableList.of(EventType.LivingUpdate);
    }

    @Override
    public Iterable<IEventListener> GetEventHandlers(EventType eventType)
    {
        switch (eventType)
        {
            case LivingUpdate:
                return this.GetLivingUpdateEventHandlers();

            default:
                return ImmutableList.of();
        }
    }

    private Iterable<IEventListener> GetLivingUpdateEventHandlers()
    {
        ImmutableList.Builder<IEventListener> resultBuilder = ImmutableList.builder();

        if (this.enableManureDrops)
        {
            resultBuilder.add(this.pigUpdateHandler);
        }

        return resultBuilder.build();
    }
}
