# pig-manure-mod
Pig Manure mod for Minecraft.

Makes adult pigs drop manure.  Manure can be used like bonemeal,
can dye things brown, and can be used as a building material.

Tested with Minecraft Forge version 11.14.0.1299

## Compiling
I don't like Eclipse, so here's a step-by-step for how to build this by hand.

Dependencies: JDK, JRE, Gradle (tested with 2.21).

### Once
1. Set the GRADLE_HOME environment variable to wherever you installed Gradle (the folder that contains bin, init.d, lib, etc.).
2. Add JDK\bin, JRE\bin, and GRADLE_HOME\bin to your PATH.

### Every time
1. Navigate to the pig-manure-mod source tree.
2. Run "gradle".
3. The result will be in the "build\libs".
