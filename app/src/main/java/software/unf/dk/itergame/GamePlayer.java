package software.unf.dk.itergame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private int attackCoolDown;

    public GamePlayer(int x, int y, MainActivity mainActivity) {
        super(x, y, mainActivity);
        xVel = 0;
        yVel = 0;
        speed = 1;
        health = 100;
        updateHealth();
        attackCoolDown = 40;
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
                    getMainActivity().remove(object);
                    setHealth(getHealth() - 10);
                }
            }
            i++;
        }


    }

    @Override
    public void draw(Canvas canvas) {

        Rect player = new Rect(getX(), getY(), getX() + 10, getY() + 10);

        if(attackAnimation > 0){

            int fadeValue = Math.abs(attackAnimation-30);

            Log.i("fadeValue", "value: " + fadeValue);

            int fade = (255) - (fadeValue/30)*255;
            Paint paint = new Paint();
            paint.setARGB(25, 255, 255, 255);

            Rect rect = new Rect(getX() - 5 - (fadeValue/2), getY() - 5 - (fadeValue/2), getX()+5+(fadeValue/2)+(player.width()), getY()+5+(fadeValue/2)+(player.height()));
            canvas.drawRect(rect, paint);

            for (GameObject object : getMainActivity().getEntities()){
                if(object instanceof EnemyProjectile){
                    if (rect.intersect(((EnemyProjectile) object).getRekt())){
                        getMainActivity().remove(object);
                    }
                }
            }

            attackAnimation -= 3;

        }

        if(attackCoolDown > 0){
            attackCoolDown--;
        }

        super.draw(canvas);
        Paint p = new Paint();
        p.setColor(Color.CYAN);
        p.setStrokeWidth(10);


        canvas.drawRect(player, p);
        //canvas.drawBitmap(getGraphic(), getX(), getY(), null);

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

        if(attackCoolDown <= 0) {
            attackAnimation = 30;
            attackCoolDown = 40;
        }

    }

    public boolean isOnCoolDown(){
        return (attackCoolDown <= 0);
    }
}
