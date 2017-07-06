package software.unf.dk.iter;

import android.graphics.Canvas;

/**
 * Created by deltager on 06-07-17.
 */

public class GameObject{

    private Canvas canvas;
    protected int x,y;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
        canvas = new Canvas();
    }

    public void setGraphic(Canvas canvas){
        this.canvas = canvas;
    }

    public boolean isColliding(GameObject firstObject, GameObject secondObject){
        firstObject
        return false;
    }

}
