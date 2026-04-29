module Core {
    requires Common;
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;
    requires jdk.compiler;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics,spring.core;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
    exports dk.sdu.mmmi.cbse.main;
}