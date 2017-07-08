package software.unf.dk.itergame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;

/**
 * Created by deltager on 07-07-17.
 */

public class GamePlayer extends GameObject {

    int xVel;
    int yVel;

    public GamePlayer(int x, int y, MainActivity mainActivity) {
        super(x, y, mainActivity);
        xVel = 0;
        yVel = 0;
        setGraphic(R.mipmap.gennemsigtig);
        scaleGraphic(700, 700, false);
    }

    @Override
    public void tick() {
        super.tick();
        setX(getX() + xVel);
        setY(getY() + yVel);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(getGraphic(), getX()-getGraphic().getWidth()/2, getY()-getGraphic().getHeight()/2, null);
    }

    public void setVelocity(int xVel, int yVel){

    }
}
