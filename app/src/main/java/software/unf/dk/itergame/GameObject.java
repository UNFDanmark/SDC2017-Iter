package software.unf.dk.itergame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by deltager on 07-07-17.
 */

public class GameObject {

    private int x;
    private int y;
    private Bitmap graphic;
    private MainActivity mainActivity;

    public GameObject(int x, int y, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void setX(int x){
        this.x = x;
    }

    protected void setY(int y){
        this.y = y;
    }

    public void tick(){

    }

    public void draw(Canvas canvas){

    }

    public Bitmap getGraphic() {
        return graphic;
    }

    protected void setGraphic(int ref){
        graphic = BitmapFactory.decodeResource(mainActivity.getResources(), ref);
    }

    protected MainActivity getMainActivity(){
        return mainActivity;
    }
}
