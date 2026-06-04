package dk.sdu.mmmi.cbse.Playersystem;

import dk.sdu.cbse.common.Player.Player;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import junit.framework.TestCase;

public class PlayerControlSystemTest extends TestCase {

    private PlayerControlSystem system;
    private GameData gameData;
    private World world;
    private Player player;

    @Override
    protected void setUp() {
        system = new PlayerControlSystem();
        gameData = new GameData();
        world = new World();
        player = new Player();

        gameData.setDisplayWidth(2000);
        gameData.setDisplayHeight(2000);
        gameData.getKeys().setKey(GameKeys.UP, true);

        player.setX(100.0);
        player.setY(200.0);
        player.setRotation(0.0);
        player.setMoveSpeed(100);
        player.setHealth(1);
        player.setTurnSpeed(250);

        world.addEntity(player);
    }

    public void testPlayerMovesForwardWhenUpIsPressed() {
        // Prime deltaTime using the public API so the test stays JPMS-friendly.
        gameData.setDeltaTime();
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError("Test interrupted while waiting for delta time", e);
        }
        gameData.setDeltaTime();

        system.process(gameData, world);

        assertTrue(player.getX() > 100.0);
        assertEquals(200.0, player.getY(), 0.0001);
    }
}



