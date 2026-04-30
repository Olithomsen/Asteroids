module Player {
    requires Common;
    requires CommonPlayer;
    requires CommonWeapon;
    requires java.desktop;
    requires javafx.graphics;
    requires CommonBullet;

    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    uses dk.sdu.cbse.common.weapon.WeaponSPI;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.Playersystem.PlayerPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.Playersystem.PlayerControlSystem;
}