package software.unf.dk.itergame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by deltager on 07-07-17.
 */

public class GamePlayer extends GameObject {

    private int xVel;
    private int yVel;
    private int speed;
    private int health;
    private int attackAnimation;

    public GamePlayer(int x, int y, MainActivity mainActivity) {
        super(x, y, mainActivity);
        xVel = 0;
        yVel = 0;
        speed = 1;
        health = 100;
        updateHealth();
    }

    @Override
    public void tick() {
        super.tick();

        int newX = getX() + xVel*speed;
        int newY = getY() + yVel*speed;

        if(!getMainActivity().getCurrentGameMap().isBlack(newX, newY)){
            setX(newX);
            setY(newY);
        }

        int i = 0;
        for(GameObject object : getMainActivity().getEntities()){
            if(object instanceof EnemyProjectile){
                EnemyProjectile projectile = (EnemyProjectile) object;
                Rect player = new Rect(getX(), getY(), getX() + 10, getY() + 10);
                if(projectile.getRekt().intersect(player)) {
                    getMainActivity().getEntities().remove(i);
                    setHealth(getHealth() - 1);
                }
            }
            i++;
        }


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint p = new Paint();
        p.setColor(Color.CYAN);
        p.setStrokeWidth(10);

        Rect player = new Rect(getX(), getY(), getX() + 10, getY() + 10);
        canvas.drawRect(player, p);
        //canvas.drawBitmap(getGraphic(), getX(), getY(), null);

        if(attackAnimation > 0){

            int fade = (255) - (((attackAnimation-30)/30)*255);
            Paint paint = new Paint();
            paint.setARGB(fade, 255, 255-((attackAnimation-30)*3), 255);

            RectF rectF = new RectF(getX() - (player.width()/2), getY()-(player.height()/2), getX()+20, getY()+20);
            canvas.drawRect(rectF, paint);

            attackAnimation--;
        }

    }

    public void setVelocity(int xVel, int yVel){
        this.xVel = xVel;
        this.yVel = yVel;

    }

    public void goToSpawn(){
        GameMap currentMap = getMainActivity().getCurrentGameMap();

        setX(currentMap.getSpawnPointX());
        setY(currentMap.getSpawnPointY());
    }

    public int getVelX() {
        return xVel;
    }

    public int getVelY() {
        return yVel;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        updateHealth();
    }

    public void updateHealth(){
        getMainActivity().setHealthText(health);
    }

    public void attack(){

        attackAnimation = 30;

    }
}
