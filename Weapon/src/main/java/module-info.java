module Weapon {
    exports dk.sdu.cbse.weapon;

    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

    requires Common;
    requires CommonWeapon;
    requires CommonBullet;

    provides dk.sdu.cbse.common.weapon.WeaponSPI with dk.sdu.cbse.weapon.WeaponControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.weapon.WeaponControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.cbse.weapon.WeaponPlugin;
}


