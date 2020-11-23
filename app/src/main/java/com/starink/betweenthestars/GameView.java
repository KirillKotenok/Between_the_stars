package com.starink.betweenthestars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private int life_count;
    private MainActivity mainActivity;
    private GameThread gameThread;
    private Timer timer;
    private int score;
    private int max_x;
    private int max_y;
    private float touchX;
    private boolean isTouch = false;

    private Bitmap[] background;

    private Bitmap life;

    private Starship starship;
    private Cosmonaut cosmonaut;
    private Asteroid asteroid;


    public GameView(Context context, int max_x, int max_y) {
        super(context);
        getHolder().addCallback(this);
        mainActivity = (MainActivity) context;
        max_x = max_x;
        max_y = max_y;
        score = 0;
        timer = new Timer();
        gameThread = new GameThread(this);
        starship = new Starship(scale_bitmap(R.drawable.starship, 180, 180), this, max_x, max_y);
        background = new Bitmap[2];
        background[0] = scale_bitmap(R.drawable.background, max_x, max_y);
        life = scale_bitmap(R.drawable.starship_life, 65, 65);
        life_count = 3;
        asteroid = new Asteroid(scale_bitmap(R.drawable.asteroid, 120, 120), this, max_x, max_y, max_x / 2);
        cosmonaut = new Cosmonaut(scale_bitmap(R.drawable.cosmonaut, 120, 120), this, max_x, max_y, max_x-30);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameThread.setRun(true);
                gameThread.start();
            }
        }, 100);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void space_draw(Canvas canvas) {
        canvas.drawBitmap(background[0], 0, 0, null);

        asteroid.drawObstacle(canvas);
        cosmonaut.drawCosmonaut(canvas);

        //Life
        for (int i = 0; i < life_count; i++) {
            int x = (int) (canvas.getWidth() - 350 + life.getWidth() * 1.5 * i);
            int y = 30;
            canvas.drawBitmap(life, x, y, null);
        }

        //asteroid collision
        if (Rect.intersects(getCollisionObject(starship.getStarship_x(), starship.getStarship_y(), starship.getStarship_model())
                , getCollisionObject(asteroid.getAsteroid_x(), asteroid.getAsteroid_y(), asteroid.getAsteroid_model()))) {
            life_count--;
            asteroid.setAsteroid_y(max_y + 30);
            if (life_count == 0) {
                mainActivity.startEndActivity(score);
                gameThread.setRun(false);
            }
            //cosmonaut collision
        }else if (Rect.intersects(getCollisionObject(starship.getStarship_x(), starship.getStarship_y(), starship.getStarship_model())
                , getCollisionObject(cosmonaut.getAsteroid_x(), cosmonaut.getAsteroid_y(), cosmonaut.getAsteroid_model()))){
            cosmonaut.setAsteroid_y(max_y + 30);
            score+=1;
        }
        if (isTouch) {
            starship.setStarship_x((int) touchX);
        }
        starship.drawCar(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isTouch = true;
        touchX = event.getRawX();
        return true;
    }

    private Bitmap scale_bitmap(Integer img_id, int size_x, int size_y) {
        Bitmap buff = BitmapFactory.decodeResource(getResources(), img_id);
        Bitmap finalBitmap = Bitmap.createScaledBitmap(buff, size_x, size_y, false);
        return finalBitmap;
    }

    public Rect getCollisionObject(int left, int top, Bitmap object_to_colission) {
        return new Rect(left, top, left + object_to_colission.getWidth(), top + object_to_colission.getHeight());
    }
}
