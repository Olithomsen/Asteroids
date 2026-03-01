import dk.sdu.mmmi.cbse.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.services.IGamePluginService;

module Player {
    requires Common;
    provides IGamePluginService with dk.sdu.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.playersystem.PlayerControlSystem;
}