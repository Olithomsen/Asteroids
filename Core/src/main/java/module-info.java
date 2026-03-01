module Core {
    requires Common;
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;

    //uses services.dk.sdu.mmmi.cbse.IGamePluginService;
    //uses services.dk.sdu.mmmi.cbse.IEntityProcessingService;
    //uses services.dk.sdu.mmmi.cbse.IPostEntityProcessingService;

    exports dk.sdu.mmmi.cbse.main;
}