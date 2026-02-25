module Core {
    requires Common;
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;

    uses dk.sdu.cbse.services.IGamePluginService;
    uses dk.sdu.cbse.services.IEntityProcessingService;
    uses dk.sdu.cbse.services.IPostEntityProcessingService;

    exports dk.sdu.cbse.core;
}