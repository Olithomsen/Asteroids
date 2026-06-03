module Collision {
    requires Common;
    requires CommonAsteroids;
    requires CommonBullet;
    requires CommonEnemy;
    requires CommonPlayer;

    exports dk.sdu.mmmi.cbse.collisionsystem;

    provides dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
}