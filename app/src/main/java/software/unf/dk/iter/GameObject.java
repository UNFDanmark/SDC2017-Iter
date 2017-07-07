package software.unf.dk.iter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by deltager on 06-07-17.
 */

public class GameObject{

    protected int x,y;
    protected Bitmap graphic;
    private MainActivity mainActivity;

    public GameObject(int x, int y, MainActivity mainActivity){
        this.x = x;
        this.y = y;
        this.mainActivity = mainActivity;
    }

    protected void setGraphic(int ref){
        graphic = BitmapFactory.decodeResource(mainActivity.getResources(), ref);
    }

    public boolean isColliding(GameObject firstObject, GameObject secondObject){
        //firstObject
        return false;
    }

    public void tick(){

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
