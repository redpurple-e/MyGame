package com.egshub.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.egshub.entities.Entity;
import com.egshub.entities.Player;
import com.egshub.graphics.Spritesheet;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 240;
    private final int HEIGHT = 160;
    private final int SCALE = 3;

    private boolean isRunning = true;

    private JFrame frame;
    private BufferedImage image;
    private Thread thread;

    public List<Entity> entitiesList;
    public Spritesheet entitiesSprite;
    
    private Player player;

    public Game() {
    	addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        entitiesList = new ArrayList<Entity>();
        entitiesSprite = new Spritesheet("/entities.png");
        player = new Player(0, 0, 16, 16, entitiesSprite.getSprite(0, 0, 16, 16));
       
        entitiesList.add(player);
    }

    public void initFrame() {
        frame = new JFrame("Meu Primeiro Jogo");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        Game game = new Game();
        game.start();
    }

    public void tick() {
        for(int i = 0; i < entitiesList.size(); i++) {
            Entity e = entitiesList.get(i);
            e.tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = image.getGraphics();
        g.setColor(new Color(0,0,0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        for(int i = 0; i < entitiesList.size(); i++) {
            Entity e = entitiesList.get(i);
            e.render(g);
        }

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
        bs.show();
    }

    @Override
    public void run() {
        requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();

        while(isRunning) {
            long now = System.nanoTime();
            
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }
            if(System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.setRight_movement(true);
		} else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.setLeft_movement(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.setUp_movement(true);
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.setDown_movement(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.setRight_movement(false);
		} else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.setLeft_movement(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.setUp_movement(false);
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.setDown_movement(false);
		}
	}
}
