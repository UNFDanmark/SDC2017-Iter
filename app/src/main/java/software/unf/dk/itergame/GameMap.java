package software.unf.dk.itergame;

import android.graphics.Canvas;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by deltager on 08-07-17.
 */

public class GameMap extends GameObject{

    private ArrayList<MapEntrance> entrances = new ArrayList<>();
    private int spawnPointX,spawnPointY;

    public GameMap(int x, int y, MainActivity mainActivity, int spawnPointX, int spawnPointY) {
        super(x, y, mainActivity);
        this.spawnPointX = spawnPointX;
        this.spawnPointY = spawnPointY;

        entrances.add(new MapEntrance(126, 72, 126, 74, MapName.second));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void addEntrance(MapEntrance mapEntrance){
        entrances.add(mapEntrance);
    }
}
