package software.unf.dk.iter;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by deltager on 06-07-17.
 */
public class GameEngine implements Runnable{

    private boolean running;
    private GameScreen gameScreen;
    //TODO: Add objects hertil
    private ArrayList<GameObject> objects = new ArrayList<>();

    public GameEngine(GameScreen screen){
        gameScreen = screen;
    }

    public void stop(){
        running = false;
    }

    public void setUp(){
        running = true;

    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running){
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
    }

    private void render(){
    }

    private void tick(){
        for(GameObject o : objects){
            o.tick();
        }
        gameScreen.setWillNotDraw(false);
        gameScreen.postInvalidate();
    }

    public void addObject(GameObject o){
        objects.add(o);
    }

    public ArrayList<GameObject> getObjects(){
        return objects;
    }

}
