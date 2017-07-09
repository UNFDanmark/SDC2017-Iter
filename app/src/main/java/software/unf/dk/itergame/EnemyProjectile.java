package software.unf.dk.itergame;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by deltager on 09-07-17.
 */

public class EnemyProjectile extends GameObject{

    int speed, size;
    Rect rect;

    public EnemyProjectile(int x, int y, MainActivity mainActivity, int speed, int size) {
        super(x, y, mainActivity);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
