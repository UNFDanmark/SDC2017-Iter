package software.unf.dk.itergame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by deltager on 09-07-17.
 */

public class EnemyProjectile extends GameObject {

    int size, xDistance, yDistance;
    Rect rekt;
    Paint colour = new Paint();
    double xV, yV, xToAdd, yToAdd, speed;


    public EnemyProjectile(int x, int y, MainActivity mainActivity, double speed, int size) {
        super(x, y, mainActivity);
        this.speed = speed;
        this.size = size;
        colour.setColor(Color.RED);
        rekt = new Rect();
        rekt.set(getX(), getY(), getX() + size, getY() + size);
    }

    @Override
    public void tick() {
        super.tick();
        if (Math.abs(xToAdd) >= 1) {
            if (xToAdd > 0) {
                setX(getX() + ((int) xToAdd));
                xToAdd -= ((int) xToAdd);
            } else {
                setX(getX() + ((int) xToAdd));
                xToAdd -= ((int) xToAdd);
            }
        }
        if (Math.abs(yToAdd) >= 1) {
            if (yToAdd > 0) {
                setY(getY() + ((int) yToAdd));
                yToAdd -= ((int) yToAdd);
            } else {
                setY(getY() + ((int) yToAdd));
                yToAdd -= ((int) yToAdd);
            }
        }
        xToAdd += xV*speed;
        yToAdd += yV*speed;


        GameMap currentMap = getMainActivity().getCurrentGameMap();

        if(getMainActivity().getCurrentGameMap().isBlack(getX(), getY())){
            getMainActivity().remove(this);
        }

        else if(getX() < currentMap.getOffsetX() || getY() < currentMap.getOffsetY()
                || getX() > (currentMap.getOffsetX() + getMainActivity().getGameCanvas().getWidth()) || getY() > (currentMap.getOffsetY() + getMainActivity().getGameCanvas().getHeight())){
            if(getMainActivity().getCurrentGameMap().isBlack(getX(), getY())){
                getMainActivity().remove(this);

            }
        }
    }

    public void directAtPlayer(){
        xDistance = getMainActivity().getGamePlayer().getX() - getX();
        yDistance = getMainActivity().getGamePlayer().getY() - getY();
        /*
        if (Math.abs(xDistance) >= Math.abs(yDistance)) {
            Log.i("directAtPlayer", "Divided by xDistance");
            xV = xDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);
            yV = yDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);

            //xToAdd = dHorisontalX / Math.abs(dHorisontalX);
            //yToAdd = dVerticalY / Math.abs(dHorisontalX);
        } else if (Math.abs(xDistance) < Math.abs(yDistance)) {
            Log.i("directAtPlayer", "Divided by yDistance");
            xV = xDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);
            yV = yDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);
            //xToAdd = dHorisontalX / Math.abs(dVerticalY);
            //yToAdd = dVerticalY / Math.abs(dVerticalY);
        }
        */
        xV = xDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);
        yV = yDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);
    }

    public void directAwayFromPlayer(){
        xDistance = getMainActivity().getGamePlayer().getX() - getX();
        yDistance = getMainActivity().getGamePlayer().getY() - getY();
        /*
        if (Math.abs(xDistance) >= Math.abs(yDistance)) {
            Log.i("directAtPlayer", "Divided by xDistance");
            xV = xDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);
            yV = yDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);

            //xToAdd = dHorisontalX / Math.abs(dHorisontalX);
            //yToAdd = dVerticalY / Math.abs(dHorisontalX);
        } else if (Math.abs(xDistance) < Math.abs(yDistance)) {
            Log.i("directAtPlayer", "Divided by yDistance");
            xV = xDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);
            yV = yDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance);
            //xToAdd = dHorisontalX / Math.abs(dVerticalY);
            //yToAdd = dVerticalY / Math.abs(dVerticalY);
        }
        */
        xV = (xDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance))*-1;
        yV = (yDistance / Math.sqrt(yDistance*yDistance+xDistance*xDistance))*-1;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        rekt.set(getX(), getY(), getX()+size, getY()+size);
        canvas.drawRect(rekt, colour);
    }

    public Rect getRekt(){
        return rekt;
    }

}

