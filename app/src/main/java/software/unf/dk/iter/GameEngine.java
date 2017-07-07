package software.unf.dk.iter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by deltager on 06-07-17.
 */
public class GameEngine implements Runnable {

    private boolean running;
    private GameScreen gameScreen;
    //TODO: Add objects hertil
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Paint background;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;

    public GameEngine(GameScreen screen, SurfaceHolder surfaceHolder) {
        gameScreen = screen;
        this.surfaceHolder = surfaceHolder;
    }

    public void stop() {
        running = false;
    }

    public void setUp() {
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
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
    }

    private void render() {
    }

    private void tick() {
        for (GameObject o : objects) {
            o.tick();
        }
        drawCanvas();
    }

    int count = 0;

    private void drawCanvas() {
        background = new Paint();
        background.setColor(Color.BLACK);

        if (count > 60) {
            System.out.println("Canvas drawn");
            count = 0;
        }
        count++;

        canvas = null;

        try {
            canvas = surfaceHolder.lockCanvas();
            System.out.println(canvas);


            synchronized (surfaceHolder) {
                canvas.drawRect(0, 0, gameScreen.getWidth(), gameScreen.getHeight(), background);

                gameScreen.draw(canvas);
            }
        } catch (Exception e) {
            System.out.println("It happened :O");
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //Draws all objects on background
            for (GameObject o : getObjects()) {
                canvas.drawBitmap(o.graphic, o.getX(), o.getY(), null);
                //Debug text
            }


            gameScreen.draw(canvas);
        }
    }

    public void addObject(GameObject o) {
        objects.add(o);
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

}
