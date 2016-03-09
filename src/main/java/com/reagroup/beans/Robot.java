package com.reagroup.beans;

import com.reagroup.constants.FACING;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Robot {

    private Position position;

    private FACING facing;

    public Robot() {
        this.setPosition(new Position(0, 0));
        this.setFacing(FACING.NORTH);
    }

    public Robot(Position position, FACING facing) {
        this.setPosition(position);
        this.setFacing(facing);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public FACING getFacing() {
        return facing;
    }

    public void setFacing(FACING facing) {
        this.facing = facing;
    }

}