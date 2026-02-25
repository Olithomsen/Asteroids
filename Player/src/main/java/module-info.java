module Player {
    requires Common;
    provides dk.sdu.cbse.services.IGamePluginService with dk.sdu.cbse.playersystem.PlayerPlugin;
    provides dk.sdu.cbse.services.IEntityProcessingService with dk.sdu.cbse.playersystem.PlayerControlSystem;
}