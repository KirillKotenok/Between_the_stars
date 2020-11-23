package com.starink.betweenthestars;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Starship {
    private int starship_x;
    private int starship_y;
    private int max_x;
    private int max_y;
    private int starship_speed;
    private Bitmap starship_model;

    private GameView gameView;

    public Starship(Bitmap car_model, GameView gameView, int max_x, int max_y) {
        this.starship_model = car_model;
        this.gameView = gameView;
        this.max_x = max_x;
        this.max_y = max_y;

        this.starship_x = max_x / 2;
        this.starship_y = max_y - car_model.getHeight() - 10;
    }

    public void drawCar(Canvas canvas) {
        canvas.drawBitmap(starship_model, starship_x, starship_y, null);
    }

    public void setStarship_x(int starship_x) {
        this.starship_x = starship_x;
    }

    public int getStarship_x() {
        return starship_x;
    }

    public Bitmap getStarship_model() {
        return starship_model;
    }

    public void setStarship_model(Bitmap starship_model) {
        this.starship_model = starship_model;
    }

    public int getStarship_y() {
        return starship_y;
    }

    public void setStarship_y(int starship_y) {
        this.starship_y = starship_y;
    }
}
