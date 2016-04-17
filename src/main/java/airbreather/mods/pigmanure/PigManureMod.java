package airbreather.mods.pigmanure;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import airbreather.mods.airbreathercore.mod.IModLifecycleManager;
import airbreather.mods.airbreathercore.mod.IModule;
import airbreather.mods.airbreathercore.mod.ModLifecycleManager;

import static com.google.common.base.Preconditions.checkNotNull;

@Mod(modid = PigManureConstants.ModID, name = PigManureConstants.ModName)
public final class PigManureMod
{
    private final IModLifecycleManager modLifecycleManager;

    public PigManureMod(final IModLifecycleManager modLifecycleManager)
    {
        this.modLifecycleManager = checkNotNull(modLifecycleManager, "modLifecycleManager");
    }

    // Either a parameterless constructor or a parameterless static factory
    // method is required for FML to load us.
    @Mod.InstanceFactory
    private static PigManureMod CreateInstance()
    {
        IModule module = new PigManureModule();
        IModLifecycleManager modLifecycleManager = new ModLifecycleManager(module);
        return new PigManureMod(modLifecycleManager);
    }

    @Mod.EventHandler
    private void PreInit(FMLPreInitializationEvent event)
    {
        this.modLifecycleManager.OnPreInit(event);

        // ehh... I don't feel like doing more stuff with airbreathercore.
        // it was an experiment, and I think it sucked.  just putting new stuff
        // into the mod itself and leaving ABC alone.
        Capability.IStorage<IPigManureTicks> storage = new Capability.IStorage<IPigManureTicks>()
        {
            @Override public NBTBase writeNBT(Capability<IPigManureTicks> capability, IPigManureTicks instance, EnumFacing side) { return null; }
            @Override public void readNBT(Capability<IPigManureTicks> capability, IPigManureTicks instance, EnumFacing side, NBTBase nbt) { }
        };

        CapabilityManager.INSTANCE.register(IPigManureTicks.class, storage, PigManureTicks.class);
        MinecraftForge.EVENT_BUS.register(new AttachCapabilitiesEventHandler());
    }

    @Mod.EventHandler
    private void Init(FMLInitializationEvent event)
    {
        this.modLifecycleManager.OnInit(event);
    }

    @Mod.EventHandler
    private void PostInit(FMLPostInitializationEvent event)
    {
        this.modLifecycleManager.OnPostInit(event);
    }
}
