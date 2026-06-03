package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import junit.framework.TestCase;

public class CollisionDetectorTest extends TestCase {
    private CollisionDetector detector;
    @Override
    protected void setUp() {
        detector = new CollisionDetector();
    }
    public void testCollidesWhenEntitiesOverlap() {
        Entity entity1 = createEntity(10.0, 10.0, 5.0f);
        Entity entity2 = createEntity(14.0, 13.0, 5.0f);

        assertTrue(detector.collides(entity1, entity2));
    }
    public void testCollidesWhenEntitiesTouchExactlyAtEdge() {
        Entity entity1 = createEntity(0.0, 0.0, 5.0f);
        Entity entity2 = createEntity(10.0, 0.0, 5.0f);

        assertFalse(detector.collides(entity1, entity2));
    }
    public void testDoesNotCollideWhenEntitiesAreSeparated() {
        Entity entity1 = createEntity(0.0, 0.0, 5.0f);
        Entity entity2 = createEntity(20.0, 20.0, 5.0f);

        assertFalse(detector.collides(entity1, entity2));
    }
    private Entity createEntity(double x, double y, float radius) {
        Entity entity = new Entity();
        entity.setX(x);
        entity.setY(y);
        entity.setRadius(radius);
        return entity;
    }
}

