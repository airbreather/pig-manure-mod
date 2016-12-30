package airbreather.mods.pigmanure;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import airbreather.mods.airbreathercore.item.ItemDefinition;

// Some constants used throughout the mod.
final class PigManureConstants
{
    // ID of the mod.
    public static final String ModID = "pigmanure";

    // ID of the base game.
    public static final String BaseGameModID = "minecraft";

    // Name of the mod.
    public static final String ModName = "Pig Manure";

    // Refers to the manure item added by this mod.
    public static final ItemDefinition ManureItemDefinition = new ItemDefinition(ModID, "manure");

    // Refers to the brick item in the base game.
    public static final ItemDefinition BrickItemDefinition = new ItemDefinition(BaseGameModID, "brick");

    // The identifier for our extended entity properties that we add to pigs.
    public static final ResourceLocation PigManureTicksIdentifier = new ResourceLocation(ModID, "PigManureTicks");

    // The lower-bound on the number of ticks between manure droppings.
    public static final int LowerBoundTicksBetweenDroppings = 6000;

    // The upper-bound on the number of ticks above and beyond LowerBoundTicksBetweenDroppings
    // that need to pass between manure droppings.
    public static final int UpperBoundAdditionalTicksBetweenDroppings = 6000;

    // Capability<T> on an Entity.  Holds # ticks left until manure time.
    @CapabilityInject(IPigManureTicks.class)
    public static final Capability<IPigManureTicks> PigManureTicksCapability = null;
}
