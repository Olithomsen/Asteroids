module Enemy {
    requires javafx.graphics;
    requires Common;
    requires CommonEnemy;
    requires CommonWeapon;

    uses dk.sdu.cbse.common.weapon.WeaponSPI;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
}