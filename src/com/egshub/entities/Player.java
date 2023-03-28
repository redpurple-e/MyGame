package com.egshub.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity {

	private boolean left_movement = false;
	private boolean right_movement = false;
	private boolean up_movement = false;
	private boolean down_movement = false;
	
	private double speed = 1;
	
    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }
    
    public void tick() {
    	if(right_movement) {
    		this.setX(getX() + speed);
    	} else if(left_movement) {
    		this.setX(getX() - speed);
    	}
    	
    	if(up_movement) {
    		this.setY(getY() - speed);
    	} else if(down_movement) {
    		this.setY(getY() + speed);
    	}
    }

	public boolean isLeft_movement() {
		return left_movement;
	}

	public void setLeft_movement(boolean left_movement) {
		this.left_movement = left_movement;
	}

	public boolean isRight_movement() {
		return right_movement;
	}

	public void setRight_movement(boolean right_movement) {
		this.right_movement = right_movement;
	}

	public boolean isUp_movement() {
		return up_movement;
	}

	public void setUp_movement(boolean up_movement) {
		this.up_movement = up_movement;
	}

	public boolean isDown_movement() {
		return down_movement;
	}

	public void setDown_movement(boolean down_movement) {
		this.down_movement = down_movement;
	}
}
