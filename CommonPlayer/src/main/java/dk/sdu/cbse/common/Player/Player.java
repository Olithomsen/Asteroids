package dk.sdu.cbse.common.Player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.cbse.common.weapon.Weapon;

public class Player extends Entity {
    private int turnSpeed = 250;
    private int moveSpeed = 1000;

    private Weapon weapon;

    public int getTurnSpeed() {
        return turnSpeed;
    }

    public void setTurnSpeed(int turnSpeed) {
        this.turnSpeed = turnSpeed;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}