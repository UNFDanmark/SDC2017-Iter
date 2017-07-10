package software.unf.dk.itergame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by deltager on 09-07-17.
 */

public class GameEnemy extends GameObject {

    private int timeTillShoot;
    private int health, shotsFired;
    private double shotsPerSecond;
    private Rect rect;
    private EnemyProjectile projectile;

    public GameEnemy(int x, int y, MainActivity mainActivity, double shotsPerSecond, int health) {
        super(x, y, mainActivity);
        this.shotsPerSecond = shotsPerSecond;
        timeTillShoot = (int) (60/shotsPerSecond);
        this.health = health;
        shotsFired = 9001;
        rect = new Rect(getX(), getY(), getX() + getMainActivity().getCurrentGameMap().getScale()*2, getY() + getMainActivity().getCurrentGameMap().getScale()*2);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        canvas.drawRect(rect, paint);
    }

    @Override
    public void tick() {
        super.tick();

        if(timeTillShoot < 0){
            EnemyProjectile projectile = new EnemyProjectile(getX()+30, getY()+30, getMainActivity(), getMainActivity().getCurrentGameMap().getScale(), getMainActivity().getCurrentGameMap().getScale()*4);
            projectile.directAtPlayer();
            this.projectile = projectile;
            projectile.
            getMainActivity().addEntity(projectile);
            timeTillShoot = (int) (60/shotsPerSecond);
        }

        timeTillShoot--;
    }

    public void damage(int damage){
        health -= damage;
    }

    public int getHealth(){
        return health;
    }



}
