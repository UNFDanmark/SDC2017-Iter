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

        if(this instanceof GameMap){
            this.x = mainActivity.getGameCanvas().getWidth()/2;
            this.y = mainActivity.getGameCanvas().getHeight()/2;
        }

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

    public boolean intersects(GameObject object){
        return false;
    }

    public Bitmap getGraphic() {
        return graphic;
    }

    protected void setGraphic(int ref){
        graphic = BitmapFactory.decodeResource(mainActivity.getResources(), ref);
    }

    protected void scaleGraphic(int width, int height, boolean smooth){
        graphic = Bitmap.createScaledBitmap(getGraphic(), width, height, smooth);
    }

    protected void setLocation(GameMap gameMap){
        setX(gameMap.getSpawnPointX());
        setY(gameMap.getSpawnPointY());
    }

    protected MainActivity getMainActivity(){
        return mainActivity;
    }
}
