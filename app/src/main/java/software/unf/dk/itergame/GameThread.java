package software.unf.dk.itergame;

import android.graphics.Canvas;
import android.support.annotation.MainThread;
import android.view.SurfaceHolder;

/**
 * Created by deltager on 07-07-17.
 */

public class GameThread extends Thread {

    public static final int maxFPS = 60;
    public double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GameCanvas gameCanvas;
    private boolean running;
    public static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, GameCanvas gameCanvas) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameCanvas = gameCanvas;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        long startTime;
        long timeMillis = 1000 / maxFPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / maxFPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameCanvas.update();
                    this.gameCanvas.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime()-startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try{
                if(waitTime > 0){
                    this.sleep(waitTime);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == maxFPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }

        }

    }

}
