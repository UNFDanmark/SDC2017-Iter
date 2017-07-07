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
        xVel = 4;
        yVel = 0;
        setGraphic(R.mipmap.slav);
    }

    @Override
    public void tick() {
        super.tick();
        setX(getX() + xVel);
        setY(getY() + yVel);
        if(getX() > getMainActivity().getGameCanvas().getWidth() -100){
            xVel = -4;
        }
        if(getX() < 100){
            xVel = 4;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(getGraphic(), getX(), getY(), null);
    }
}
