package software.unf.dk.itergame;

import android.graphics.Canvas;

/**
 * Created by deltager on 09-07-17.
 */

public class GameEnemy extends GameObject {

    private int timeTillShoot;
    private int health;

    public GameEnemy(int x, int y, MainActivity mainActivity, double shotsPerSecond, int health) {
        super(x, y, mainActivity);
        timeTillShoot = (int) (60/shotsPerSecond);
        this.health = health;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


    }

    @Override
    public void tick() {
        super.tick();
    }

    public void damage(int damage){
        health -= damage;
    }

    public int getHealth(){
        return health;
    }



}
