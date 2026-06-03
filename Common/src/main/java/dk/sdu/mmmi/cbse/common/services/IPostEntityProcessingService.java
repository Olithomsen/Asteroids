package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IPostEntityProcessingService {

    /**
     * Performs post-processing operations on entities after the main processing cycle.
     * <p>
     * Common use cases:
     * <ul>
     *   <li>Collision detection: checks all entity pairs for intersections.</li>
     *   <li>Damage application: applies effects from detected collisions (health reduction, destruction).</li>
     *   <li>Score updates: increments score when enemies or asteroids are destroyed.</li>
     *   <li>Asteroid splitting: creates smaller asteroids when a large one is hit.</li>
     *   <li>Game state checks: determines win/lose conditions.</li>
     * </ul>
     * </p>
     * <p>
     * <b>Pre-condition:</b> All IEntityProcessingService implementations have completed in the current frame.
     * All entities are in their final positions for this frame. gameData and world are non-null.<br>
     * <b>Post-condition:</b> Inter-entity interactions have been evaluated and applied.
     * Entities may have been modified (health reduced, destroyed) as a result of interactions.
     * New entities may be added (split asteroids). Destroyed entities are removed from the world.
     * </p>
     *
     * @param gameData shared game state containing delta time and display dimensions
     * @param world    shared entity collection that may be modified based on collision/interaction results
     * @throws IllegalArgumentException if gameData or world is null
     */
    void process(GameData gameData, World world);
}
