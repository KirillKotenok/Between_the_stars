package com.starink.betweenthestars;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Asteroid {
    private int asteroid_x;
    private int asteroid_y;
    private int max_x;
    private int max_y;
    private Bitmap asteroid_model;
    private int asteroid_speed = 30;
    private Random random;
    private GameView gameView;

    public Asteroid(Bitmap car_model, GameView gameView, int max_x, int max_y, int obstacle_x) {
        this.asteroid_model = car_model;
        this.gameView = gameView;
        this.max_x = max_x;
        this.max_y = max_y;
        this.asteroid_x = obstacle_x;
        this.asteroid_y = max_y - car_model.getHeight();
        random = new Random();
    }


    private void updateObstacle() {
        asteroid_y += asteroid_speed;
    }

    public void obstacleIsOut() {
        if (asteroid_y >= max_y) {
            asteroid_y = -10;
            asteroid_x = random.nextInt(max_x - max_x / 3) +1;
        }
    }

    public void drawObstacle(Canvas canvas) {
        updateObstacle();
        obstacleIsOut();
        canvas.drawBitmap(asteroid_model, asteroid_x, asteroid_y, null);
    }

    public int getAsteroid_x() {
        return asteroid_x;
    }

    public void setAsteroid_x(int asteroid_x) {
        this.asteroid_x = asteroid_x;
    }

    public int getAsteroid_y() {
        return asteroid_y;
    }

    public void setAsteroid_y(int asteroid_y) {
        this.asteroid_y = asteroid_y;
    }

    public Bitmap getAsteroid_model() {
        return asteroid_model;
    }

    public void setAsteroid_model(Bitmap asteroid_model) {
        this.asteroid_model = asteroid_model;
    }
}
