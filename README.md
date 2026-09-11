# Arclight 1.20.1 (Optimized Source Code)

Source code and bytecode engine optimizations for Arclight Forge 1.20.1 server.

## Features & Custom Optimizations

- **Bukkit Event Dispatch Fast-Path (`SimplePluginManager`)**:
  - Implemented early-exit in `callEvent(Event)` when no Bukkit listener plugins are loaded or handler lists are empty, eliminating event dispatch overhead and heap allocations during entity and chunk ticks.
- **Atomic Custom Timings Handler (`CustomTimingsHandler`)**:
  - Added volatile cached status check (`timingsEnabled`) to skip `System.nanoTime()` and deep method stack traversals when timings are disabled.
- **Mob Target & AI Optimization (`MobMixin`)**:
  - Silenced synchronous `LOGGER.warn("Unknown target reason...")` I/O on the main thread during high-frequency combat target switches.
  - Skipped Bukkit event wrapper allocations (`CraftLivingEntity`, `EntityTargetLivingEntityEvent`) when no Spigot/Bukkit plugins exist, routing directly through Forge event bus.
- **Activation Range & Entity Brains**:
  - Optimized activation checks and bitwise interval calculations.
