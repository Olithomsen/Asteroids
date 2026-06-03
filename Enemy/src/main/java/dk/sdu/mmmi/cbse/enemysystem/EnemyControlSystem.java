package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.weapon.WeaponSPI;
import java.util.Random;
import java.util.ServiceLoader;


public class EnemyControlSystem implements IEntityProcessingService {

    private final Random rand = new Random();
    private final EnemyPlugin enemyPlugin = new EnemyPlugin();
    private final ServiceLoader<WeaponSPI> weaponLoader = ServiceLoader.load(WeaponSPI.class);

    private static final double SPAWN_RATE = 20;
    private double spawnTimer = 10;

    @Override
    public void process(GameData gameData, World world) {

        spawnTimer += gameData.getDeltaTime();

        if (spawnTimer >= SPAWN_RATE) {
            spawnTimer -= SPAWN_RATE;
            world.addEntity(enemyPlugin.createEnemy(gameData));
        }

        for (Entity entity : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) entity;

            if (enemy.getHealth() <= 0) {
                world.removeEntity(enemy);
                continue;
            }

            if (enemy.getWeapon() != null) {
                enemy.getWeapon().setIsShooting(true);
            } else {
                weaponLoader.stream().map(ServiceLoader.Provider::get).findFirst().ifPresent(spi -> {
                    enemy.setWeapon(spi.createWeapon(enemy));
                    enemy.getWeapon().setFireRate(1);
                    world.addEntity(enemy.getWeapon());
                });
            }

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

            int direction = rand.nextInt(5) - 2;
            enemy.setRotation(enemy.getRotation() + direction * enemy.getTurnSpeed() * gameData.getDeltaTime());

            enemy.setX(enemy.getX() + changeX * enemy.getMoveSpeed() * gameData.getDeltaTime());
            enemy.setY(enemy.getY() + changeY * enemy.getMoveSpeed() * gameData.getDeltaTime());

            if (enemy.getX() < 0) {
                enemy.setX(enemy.getX() + gameData.getDisplayWidth());
            }
            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(enemy.getX() % gameData.getDisplayWidth());
            }
            if (enemy.getY() < 0) {
                enemy.setY(enemy.getY() + gameData.getDisplayHeight());
            }
            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(enemy.getY() % gameData.getDisplayHeight());
            }
        }
    }
}