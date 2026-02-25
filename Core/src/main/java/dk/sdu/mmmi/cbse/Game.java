package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class Game {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServiceList;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;

    Game(List<IGamePluginService> gamePluginServices,
         List<IEntityProcessingService> entityProcessingServiceList,
         List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServiceList = entityProcessingServiceList;
        this.postEntityProcessingServices = postEntityProcessingServices;
    }

    public void start(Stage window) {
        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(text);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> handleKeyInput(event.getCode(), true));
        scene.setOnKeyReleased(event -> handleKeyInput(event.getCode(), false));

        for (IGamePluginService iGamePlugin : getGamePluginServices()) {
            iGamePlugin.start(gameData, world);
        }

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void handleKeyInput(KeyCode code, boolean isPressed) {
        switch (code) {
            case LEFT -> gameData.getKeys().setKey(GameKeys.LEFT, isPressed);
            case RIGHT -> gameData.getKeys().setKey(GameKeys.RIGHT, isPressed);
            case UP -> gameData.getKeys().setKey(GameKeys.UP, isPressed);
            case SPACE -> gameData.getKeys().setKey(GameKeys.SPACE, isPressed);
            default -> {}
        }
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        // Iterator safely removes discarded entities from both map and scene graph
        var iterator = polygons.entrySet().iterator();
        while (iterator.hasNext()) {
            var entry = iterator.next();
            if (!world.getEntities().contains(entry.getKey())) {
                gameWindow.getChildren().remove(entry.getValue());
                iterator.remove();
            }
        }

        for (Entity entity : world.getEntities()) {
            // computeIfAbsent natively handles null checking and map insertion
            Polygon polygon = polygons.computeIfAbsent(entity, e -> {
                Polygon p = new Polygon(e.getPolygonCoordinates());
                gameWindow.getChildren().add(p);
                return p;
            });

            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }
    }

    public List<IGamePluginService> getGamePluginServices() {
        return gamePluginServices;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServiceList;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }
}