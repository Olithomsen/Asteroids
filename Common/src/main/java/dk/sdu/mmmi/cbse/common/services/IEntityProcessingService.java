package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Processes all relevant entities in the world for a single frame.
     * <p>
     * Typical implementations:
     * <ul>
     *   <li>Player control system: reads input keys and updates player position/rotation.</li>
     *   <li>Enemy control system: updates enemy movement and weapon state.</li>
     *   <li>Bullet control system: updates bullet positions and removes out-of-bounds bullets.</li>
     *   <li>Asteroid control system: updates asteroid movement and split states.</li>
     * </ul>
     * </p>
     * <p>
     * <b>Pre-condition:</b> gameData.getDeltaTime() is valid and > 0. World contains zero or more entities
     * to process. No entities are expected to be modified concurrently by other threads.<br>
     * <b>Post-condition:</b> Relevant entities have been updated according to game logic.
     * New entities may be added to the world (e.g., bullets when player fires, smaller asteroids on split).
     * Entities may be removed from the world (e.g., bullets that leave the screen).
     * </p>
     *
     * @param gameData shared game state containing delta time, key input state, and display dimensions
     * @param world    shared entity collection containing entities to process and where new/removed entities are tracked
     * @throws IllegalArgumentException if gameData or world is null
     */
    void process(GameData gameData, World world);
}
