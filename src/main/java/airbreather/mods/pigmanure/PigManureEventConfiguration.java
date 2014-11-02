package airbreather.mods.pigmanure;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.eventhandler.IEventListener;

import airbreather.mods.airbreathercore.event.EventConfiguration;
import airbreather.mods.airbreathercore.event.EventType;

import static com.google.common.base.Preconditions.checkNotNull;

// Holds event-related configuration information, specific to YAFM.
final class PigManureEventConfiguration implements EventConfiguration
{
    private final PigUpdateEventHandler pigUpdateHandler;
    private final PigConstructingEventHandler pigConstructingHandler;

    private boolean enableManureDrops = false;

    public PigManureEventConfiguration(PigUpdateEventHandler pigUpdateHandler,
                                       PigConstructingEventHandler pigConstructingHandler)
    {
        this.pigUpdateHandler = checkNotNull(pigUpdateHandler, "pigUpdateHandler");
        this.pigConstructingHandler = checkNotNull(pigConstructingHandler, "pigConstructingHandler");
    }

    public void EnableManureDrops()
    {
        this.enableManureDrops = true;
    }

    @Override
    public Iterable<EventType> GetRecognizedEventTypes()
    {
        return ImmutableList.of(EventType.LivingUpdate, EventType.EntityConstructing);
    }

    @Override
    public Iterable<IEventListener> GetEventHandlers(EventType eventType)
    {
        switch (eventType)
        {
            case LivingUpdate:
                return this.GetLivingUpdateEventHandlers();

            case EntityConstructing:
                return this.GetEntityConstructingEventHandlers();

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

    private Iterable<IEventListener> GetEntityConstructingEventHandlers()
    {
        ImmutableList.Builder<IEventListener> resultBuilder = ImmutableList.builder();

        if (this.enableManureDrops)
        {
            resultBuilder.add(this.pigConstructingHandler);
        }

        return resultBuilder.build();
    }
}
