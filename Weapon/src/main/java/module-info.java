
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.weapon.WeaponControlSystem;
import dk.sdu.cbse.weapon.WeaponPlugin;
import dk.sdu.cbse.common.weapon.WeaponSPI;

module Weapon {
    exports dk.sdu.cbse.weapon;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    requires Common;
    requires CommonWeapon;
    requires CommonBullet;

    provides WeaponSPI with WeaponControlSystem;
    provides IEntityProcessingService with WeaponControlSystem;
    provides IGamePluginService with WeaponPlugin;
}
