package com.egshub.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {

	private boolean left_movement = false;
	private boolean right_movement = false;
	private boolean up_movement = false;
	private boolean down_movement = false;
	
	private double speed = 1; // não reconhece numeros < 1

	private int frames = 0, max_frames = 20;
	private int index = 0, max_index = 1;
	
	private BufferedImage[] forwardPlayer;
	private BufferedImage[] backwardPlayer;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, null);
        
        forwardPlayer = new BufferedImage[2];
        backwardPlayer = new BufferedImage[2];
    	rightPlayer = new BufferedImage[2];
    	leftPlayer = new BufferedImage[2];

        backwardPlayer[0] = entitiesSprite.getSprite(0, 0, 16, 16);
        backwardPlayer[1] = entitiesSprite.getSprite(16, 0, 16, 16);
        forwardPlayer[0] = entitiesSprite.getSprite(32, 0, 16, 16);
        forwardPlayer[1] = entitiesSprite.getSprite(48, 0, 16, 16);
        rightPlayer[0] = entitiesSprite.getSprite(64, 0, 16, 16);
        rightPlayer[1] = entitiesSprite.getSprite(80, 0, 16, 16);
        leftPlayer[0] = entitiesSprite.getSprite(96, 0, 16, 16);
        leftPlayer[1] = entitiesSprite.getSprite(112, 0, 16, 16);
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
    	
		frames++;
		if(frames >= max_frames) {
			frames = 0;
			index++;
			if(index > max_index) {
				index = 0;
			}
    	}
    }
    
    public void render(Graphics g) {
    	if(right_movement) {
    		g.drawImage(rightPlayer[index], getX(), getY(), null);
    	} else if(left_movement) {
    		g.drawImage(leftPlayer[index], getX(), getY(), null);
    	} else if(up_movement) {
    		g.drawImage(forwardPlayer[index], getX(), getY(), null);
    	} else {
    		g.drawImage(backwardPlayer[index], getX(), getY(), null);
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
