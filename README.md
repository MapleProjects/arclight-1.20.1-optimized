# Arclight 1.20.1 (Ultra-Optimized Server Core)

Source code, ASM transformers, and bytecode engine optimizations for Arclight Forge 1.20.1 server.

## Features & Custom Optimizations

### 1. Procedural World Generation & Multi-Threading
- **Dynamic Hardware Thread Scaling (`ApplicationBootstrap` & `BootstrapMixin`)**:
  - Automatically sizes `Util.BACKGROUND_EXECUTOR` to all available CPU threads (`Runtime.getRuntime().availableProcessors()`), unlocking 16+ parallel workers for terrain noise, biome interpolation, and structure carving.
- **Lockless Structure Block Iteration (`StructureTemplate_PaletteMixin`)**:
  - Replaced high-allocation Java Stream pipelines (`stream().filter().collect()`) with iterative zero-allocation loops during structure placement.
- **Asynchronous POI Mismatch Silencing (`PoiSectionMixin`)**:
  - Suppressed disk I/O lock contention on `PoiSection.remove()` during rapid multi-chunk carving in high-speed flight.

### 2. Main Thread Event Loop & Chunk Drainage
- **Batch Chunk Completion Drainage (`ServerChunkCache_MainThreadExecutorMixin`)**:
  - Drains up to 128 completed async chunk futures per tick on the main thread, preventing main-thread backlog during high-speed exploration (view-distance 32).
- **Callback Executor Expansion (`ArclightCallbackExecutor`)**:
  - Expanded callback task processing cap from 64 to 512 tasks and from 5ms to 20ms per tick, ensuring async chunk generation results are immediately processed and dispatched.

### 3. Network & Packet Pipeline
- **High-Throughput Chunk Dispatching (`ApplicationBootstrap`)**:
  - Raised `paper.max-chunk-sends-per-tick` to 1024.
  - Enabled `paper.explicit-flush=true` for immediate Netty socket flushing without intermediate buffer stalls.
  - Enabled `io.netty.allocator.type=pooled` and `Paper.asyncChunks=true`.

### 4. Bukkit & Forge Bridge Optimizations
- **Event Dispatch Fast-Path (`SimplePluginManager`)**:
  - Early-exit in `callEvent(Event)` when no Bukkit listener plugins are registered.
- **Fast Entity Tracking (`ChunkMapMixin_Optimize`)**:
  - Paper dirty player tracker and `ReferenceOpenHashSet` for player connections.
- **Non-blocking Data Managers (`EntityDataManagerMixin_Optimize`)**:
  - Replaced `ReentrantReadWriteLock` with `NoopReadWriteLock` on entity synchronized data.

## Build & Patching

To build and patch `arclight-forge-1.20.1-1.0.6-SNAPSHOT.jar`:
```bash
javac -cp "libraries/org/ow2/asm/asm/9.7.1/asm-9.7.1.jar:libraries/org/ow2/asm/asm-tree/9.7.1/asm-tree-9.7.1.jar:libraries/org/ow2/asm/asm-commons/9.7.1/asm-commons-9.7.1.jar" tools/ArclightPatcher.java
java -cp "libraries/org/ow2/asm/asm/9.7.1/asm-9.7.1.jar:libraries/org/ow2/asm/asm-tree/9.7.1/asm-tree-9.7.1.jar:libraries/org/ow2/asm/asm-commons/9.7.1/asm-commons-9.7.1.jar:tools" ArclightPatcher
```
