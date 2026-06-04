package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
public interface IGamePluginService {

    /**
     * Initializes the plugin and adds initial game entities to the world.
     * <p>
     * <b>Pre-condition:</b> gameData and world are non-null and valid.<br>
     * <b>Post-condition:</b> Plugin has initialized any necessary entities and added them to the world.
     * Game state has been updated to reflect the presence of this plugin.
     * </p>
     *
     * @param gameData shared game state (display dimensions, delta time, input keys, etc.)
     * @param world    shared entity collection where plugin-managed entities are registered
     * @throws IllegalStateException if the plugin is already initialized or if initialization fails
     */
    void start(GameData gameData, World world);

    /**
     * Shutdown hook: cleans up plugin resources and removes all plugin-managed entities from the world.
     * <p>
     * <b>Pre-condition:</b> The plugin has been started. gameData and world are non-null.<br>
     * <b>Post-condition:</b> All entities created by this plugin have been removed from the world.
     * Plugin resources are released and the plugin is ready to be unloaded.
     * </p>
     *
     * @param gameData shared game state
     * @param world    shared entity collection from which plugin entities are removed
     * @throws IllegalStateException if the plugin has not been started or cleanup fails
     */
    void stop(GameData gameData, World world);
}
