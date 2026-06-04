module Asteroid {
    requires Common;
    requires CommonAsteroids;
    requires javafx.graphics;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.asteroids.AsteroidPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.asteroids.AsteroidControlSystem;
}
