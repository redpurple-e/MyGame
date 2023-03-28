package com.egshub.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
    
	private double x, y;
    private int width, height;
    private BufferedImage sprite;

    public Entity(double x, double y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public void tick() {}

    public void render(Graphics g) {
        g.drawImage(sprite, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);

    }

	public int getX() {
		return (int)x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
    
}
