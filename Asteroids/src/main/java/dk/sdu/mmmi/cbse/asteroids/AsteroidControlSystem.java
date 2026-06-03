
package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidControlSystem implements IEntityProcessingService{

    private final IAsteroidSplitter asteroidSplitter = new AsteroidSplitter();

    private double spawnTimer = 0.0;
    private static final double spawnRate = 5;

    @Override
    public void process(GameData gameData, World world) {

        spawnTimer += gameData.getDeltaTime();

        if (spawnTimer >= spawnRate) {
            spawnTimer -= spawnRate;

            world.addEntity(AsteroidPlugin.createAsteroid(gameData));
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {



            if (asteroid.getHealth() <= 0) {
                world.removeEntity(asteroid);
                continue;
            }

            if (asteroidSplitter != null) {
                if (((Asteroid) asteroid).isHit()) {
                    asteroidSplitter.createSplitAsteroid(asteroid, world);
                    ((Asteroid) asteroid).setHit(false);
                }
            }

            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * asteroid.getMoveSpeed() * gameData.getDeltaTime());
            asteroid.setY(asteroid.getY() + changeY * asteroid.getMoveSpeed() * gameData.getDeltaTime());

            if (asteroid.getX() < 0) {
                asteroid.setX(asteroid.getX() + gameData.getDisplayWidth());
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(asteroid.getY() + gameData.getDisplayHeight());
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
            }

        }

    }
}
