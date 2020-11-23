package com.starink.betweenthestars;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Cosmonaut {
    private int cosmonaut_x;
    private int cosmonaut_y;
    private int max_x;
    private int max_y;
    private Bitmap cosmonaut_model;
    private int cosmonaut_speed = 20;
    private Random random;
    private GameView gameView;

    public Cosmonaut(Bitmap cosmonaut_model, GameView gameView, int max_x, int max_y, int cosmonaut_x) {
        this.cosmonaut_model = cosmonaut_model;
        this.gameView = gameView;
        this.max_x = max_x;
        this.max_y = max_y;
        this.cosmonaut_x = cosmonaut_x;
        this.cosmonaut_y = max_y - cosmonaut_model.getHeight();
        random = new Random();
    }


    private void updateObstacle() {
        cosmonaut_y += cosmonaut_speed;
    }

    public void obstacleIsOut() {
        if (cosmonaut_y >= max_y) {
            cosmonaut_y = -10;
            cosmonaut_x = random.nextInt(max_x) + 1;
        }
    }

    public void drawCosmonaut(Canvas canvas) {
        updateObstacle();
        obstacleIsOut();
        canvas.drawBitmap(cosmonaut_model, cosmonaut_x, cosmonaut_y, null);
    }

    public int getAsteroid_x() {
        return cosmonaut_x;
    }

    public void setAsteroid_x(int asteroid_x) {
        this.cosmonaut_x = asteroid_x;
    }

    public int getAsteroid_y() {
        return cosmonaut_y;
    }

    public void setAsteroid_y(int asteroid_y) {
        this.cosmonaut_y = asteroid_y;
    }

    public Bitmap getAsteroid_model() {
        return cosmonaut_model;
    }

    public void setAsteroid_model(Bitmap asteroid_model) {
        this.cosmonaut_model = asteroid_model;
    }
}
