package AsteriodGame;

import static AsteriodGame.Constants.*;


import java.awt.Color;
import java.awt.Graphics2D;

import Utilities.Vector2D;

public class Bullet extends GameObject {

    private double BulletLifeTime;
    public static final double RADIUS = 2;
    private boolean isAlive = true;
    public static final int BULLET_LIFE = 2;


    // start position will the position of the ship's origin
    public Bullet(double x, double y, double vx, double vy) {
        super(new Vector2D(x, y), new Vector2D(vx, vy), RADIUS);
        this.BulletLifeTime = BULLET_LIFE;

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive() {
        isAlive = false;
    }

    @Override
    public void update() {
        super.position.addScaled(super.velocity, 0.1);
        BulletLifeTime -= DT;
        if (BulletLifeTime <= 0 || super.position.x < 0 || super.position.x > FRAME_WIDTH || super.position.y < 0 || super.position.y > FRAME_HEIGHT) {
            System.out.println("Bullet left screen = dead or lifetime is done");
            setAlive();

        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) position.x, (int) position.y, 5, 5);
    }
}
