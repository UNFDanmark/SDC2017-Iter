package software.unf.dk.itergame;

import android.graphics.Point;

/**
 * Created by deltager on 08-07-17.
 */

public class MapEntrance {

    private int x1,x2,y1,y2;
    private MapName direction;

    public MapEntrance(int x1, int x2, int y1, int y2, MapName direction){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.direction = direction;
    }

    public MapName getDirection() {
        return direction;
    }

    public void setDirection(MapName direction) {
        this.direction = direction;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }
}
