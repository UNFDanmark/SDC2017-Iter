package software.unf.dk.itergame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by deltager on 08-07-17.
 */

public class GameMap extends GameObject{

    private ArrayList<MapEntrance> entrances = new ArrayList<>();
    private int spawnPointX,spawnPointY, scale, width, height;
    private MapName mapName;
    private boolean scaled;


    public GameMap(int x, int y, MainActivity mainActivity, int spawnPointX, int spawnPointY, MapName mapName) {
        super(x, y, mainActivity);
        this.spawnPointX = spawnPointX;
        this.spawnPointY = spawnPointY;
        this.mapName = mapName;

        switch(mapName){
            case entrance:
                setGraphic(R.drawable.entrance);
                break;
            case second:
                setGraphic(R.mipmap.room_second);
                break;
            default:
                break;
        }

        scaled = false;

        entrances.add(new MapEntrance(126, 72, 126, 74, MapName.second));
    }

    public int getOffsetX(){



        return getMainActivity().getGameCanvas().getWidth()/2 - getGraphic().getWidth()/2;
    }

    public int getOffsetY(){
        return getMainActivity().getGameCanvas().getHeight()/2 - getGraphic().getHeight()/2;
    }

    public boolean isBlack(int x, int y){
        x = x - getOffsetX();
        y = y - getOffsetY();

        try {
            int pixel = getGraphic().getPixel(x, y);
            if(pixel == Color.BLACK){
                return true;
            }
        }catch(IllegalArgumentException e){
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if(!scaled){

            //Width er samme som Height
            int scaleValue = getGraphic().getWidth();


            int pixelPerfect = 0;

            if(canvas.getHeight() >= canvas.getWidth()){
                int width = canvas.getWidth();
                pixelPerfect = width % scaleValue;


                scaleGraphic(canvas.getWidth() - pixelPerfect, canvas.getWidth() - pixelPerfect, false);
                scale = (canvas.getWidth() - pixelPerfect)/scaleValue;
            }else{
                int height = canvas.getHeight();
                pixelPerfect = height % scaleValue;


                scaleGraphic(canvas.getHeight() - pixelPerfect, canvas.getHeight() - pixelPerfect, false);
                scale = (canvas.getHeight() - pixelPerfect)/scaleValue;
            }

            spawnPointX = (canvas.getWidth()/2 - getGraphic().getWidth()/2) + spawnPointX*getScale();
            spawnPointY = (canvas.getHeight()/2 - getGraphic().getHeight()/2) + spawnPointY*getScale();

            scaled = true;
        }


        canvas.drawBitmap(getGraphic(), canvas.getWidth()/2-getGraphic().getWidth()/2, canvas.getHeight()/2-getGraphic().getWidth()/2, null);
    }

    public void addEntrance(MapEntrance mapEntrance){
        entrances.add(mapEntrance);
    }

    public MapName getMapName() {
        return mapName;
    }

    public ArrayList<MapEntrance> getEntrances() {
        return entrances;
    }

    public int getSpawnPointX() {
        return spawnPointX;
    }

    public int getSpawnPointY() {
        return spawnPointY;
    }

    public int getScale() {
        return scale;
    }

    public boolean hasScaled(){
        return scaled;
    }

    //Get it?
    public void spawnEnemy(int enemX, int enemY){
        GameEnemy enemy1 = new GameEnemy(enemX+getOffsetX(), enemY+getOffsetY(), getMainActivity(), 0.2, 3);
        getMainActivity().addEntity(enemy1);
    }


}
