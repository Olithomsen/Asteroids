module Bullet {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;

    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.bullet.BulletPlugin;
    provides dk.sdu.mmmi.cbse.common.bullet.BulletSPI with dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
}
